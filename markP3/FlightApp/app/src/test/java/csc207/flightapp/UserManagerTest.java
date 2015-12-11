package csc207.flightapp;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.BeforeClass;
import org.junit.Test;

import backend.UserManager;
import backend.User;
import backend.Admin;
import backend.Client;

public class UserManagerTest {

    private static UserManager um;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static boolean setUpDone = false;

    //Constructor
    // getInstance
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        um = UserManager.getInstance();
        assertTrue(um.getUsers().isEmpty());
        assertSame(UserManager.getInstance(), um);
        setUpDone = true;
    }

    // add User updates and adds
    @Test
    public void addUserWorksCorrectly() {
        int startSize = um.getUsers().size();
        // add a user new to the system
        User newUser = new User("sooham@gmail.com", "password");
        um.addUser(newUser);

        assertEquals(um.getUsers().size(), startSize + 1);
        assertTrue(um.getUsers().contains(newUser));

        Admin newAdmin = new Admin("admin@flightapp.com", "adminpass");
        Client newClient = new Client("client@flightapp.com", "clientpass");

        um.addUser(newAdmin);
        um.addUser(newClient);

        assertEquals(um.getUsers().size(), startSize + 3);
        assertTrue(um.getUsers().contains(newAdmin));
        assertTrue(um.getUsers().contains(newClient));

        // add a user previously in the system overwrites
        assertEquals(um.getUserWithEmail("client@flightapp.com").getPassword
                (), "clientpass");
        assertEquals(um.getUserWithEmail("sooham@gmail.com").getPassword
                (), "password");

        User editedUser = new User("client@flightapp.com", "newclientpass");
        Admin editedUser2 = new Admin("sooham@gmail.com", "shinynewpass");

        um.addUser(editedUser);
        um.addUser(editedUser2);

        assertEquals(um.getUsers().size(), startSize + 3);
        assertEquals(um.getUserWithEmail("client@flightapp.com").getPassword
                (), "newclientpass");
        assertEquals(um.getUserWithEmail("sooham@gmail.com").getPassword
                (), "shinynewpass");
    }

    // loginCredientialsCorrect returns correct for user in, not in manager
    // with correct, incorrect email password combos
    @Test
    public void loginCredintialsCorrectWorks() {
        Admin newAdmin = new Admin("admin", "pass");
        Client newClient = new Client("client", "clientpwd");
        um.addUser(newAdmin);
        um.addUser(newClient);

        // works with user in manager
        assertTrue(um.loginCredentialsCorrect("admin", "pass"));
        assertTrue(um.loginCredentialsCorrect("client", "clientpwd"));

        assertFalse(um.loginCredentialsCorrect("client", "pass"));
        assertFalse(um.loginCredentialsCorrect("admin", "wpass"));
        assertFalse(um.loginCredentialsCorrect("Admin", "pass"));

        assertFalse(um.loginCredentialsCorrect("DNE", "pass"));
        assertFalse(um.loginCredentialsCorrect("DNE", "clientpwd"));
    }

    // getUserWithEmail returns correct user and null
    @Test
    public void getUserWithEmailWorks() {
        Admin newAdmin = new Admin("jane@smith.com", "sleek");
        um.addUser(newAdmin);

        // a user that does not exist in the system
        assertTrue(!um.getUsers().contains(new User("thisuserdoesnot@exist" +
                ".com", "")));
        assertNull(um.getUserWithEmail("thisuserdoesnot@exist.com"));

        // a user that exists
        assertTrue(um.getUsers().contains(newAdmin));
        assertEquals(um.getUserWithEmail("jane@smith.com"), newAdmin);
        assertEquals(um.getUserWithEmail("jane@smith.com").getPassword(),
                "sleek");
    }

    // getUsers

}
