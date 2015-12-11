package csc207.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import backend.Admin;
import backend.FileDatabase;
import backend.UserManager;

public class UserLogin extends AppCompatActivity {

    private UserManager userManager;
    public static final String EMAIL = "csc207.FlightApp.EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // making the FileDatabase instance

        try {
            FileDatabase.createInstance(
                    this.getApplicationContext().getFilesDir(
                    ).getCanonicalPath() + "/"
            );
        }catch (IOException e){
            // Unable to get the local app storage
            TextView textView = (TextView) findViewById(R.id.login_failure);
            textView.setText("App storage failure");
        }

        // get the UserManager
        userManager = FileDatabase.getInstance().getUserManager();
        setContentView(R.layout.activity_user_login2);
    }

    /**
     * Called when the login button in UserLogin activity is pressed.
     * This method determines if the user email passed belongs to a
     * Client or Admin, starting the searchForFlights or AdminMenu activities
     * respectively.
     *
     * @param view the view where the button is pressed.
     */
    public void searchFlightIntent(View view) {
        // get the email and password
        String email = ((EditText) findViewById(R.id.get_email)).getText(
        ).toString();
        String password = ((EditText) findViewById(R.id.get_password)).getText(
        ).toString();

        // confirm UserCredentials are correct

        if (userManager.loginCredentialsCorrect(email, password)) {
            // check if the User is Admin or Client
            Intent transitionIntent;
            if (userManager.getUserWithEmail(email) instanceof Admin) {
               transitionIntent = new Intent(this, AdminMenu.class);
            } else {
                transitionIntent = new Intent(this, SearchForFlights.class);
            }
            transitionIntent.putExtra(EMAIL, email);
            startActivity(transitionIntent);
        } else {
            TextView textView = (TextView) findViewById(R.id.login_failure);
            textView.setText("Password or Username is incorrect. ");
        }
    }
}
