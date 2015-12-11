package csc207.flightapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class AdminMenu extends Activity {

    public static final String EDIT_CLIENT = "csc207.FlightApp.EDIT_CLIENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
    }

    /**
     * Called to reroute the admin to search and edit Flights
     * or Itineraries.
     *
     * @param view the view of the button clicked
     */
    public void adminSearch(View view) {
        // go to the Search for flights activity with the admin flag
        Intent intent = new Intent(this, SearchForFlights.class);
        intent.putExtra(UserLogin.EMAIL, getIntent().getStringExtra(
                UserLogin.EMAIL));
        startActivity(intent);
    }

    /**
     * Called to reroute the admin to search and edit Clients
     * or Itineraries.
     *
     * @param view the view of the button clicked
     */
    public void adminViewEditClients(View view) {
        // go to the Search for clients activity with the admin flag
        Intent intent = new Intent(this, SearchForClients.class);
        intent.putExtra(UserLogin.EMAIL, getIntent().getStringExtra(UserLogin
                .EMAIL));
        intent.putExtra(AdminMenu.EDIT_CLIENT, true);
        startActivity(intent);
    }

    /**
     * Called to reroute the admin to upload CSV files
     * or Itineraries.
     *
     * @param view the view of the button clicked
     */
    public void adminUpload(View view) {
        // go to the upload activity (admin only)
        Intent intent = new Intent(this, Upload.class);
        startActivity(intent);
    }

    /**
     * Called to reroute the admin to view or book Itineraries for the Client
     * or Itineraries.
     *
     * @param view the view of the button clicked
     */
    public void adminBookForClient(View view) {
        // go to the book for client activity (admin only)
        Intent intent = new Intent(this, SearchForClients.class);
        intent.putExtra(UserLogin.EMAIL, getIntent().getStringExtra(UserLogin
                .EMAIL));

        intent.putExtra(AdminMenu.EDIT_CLIENT, false);
        startActivity(intent);
    }

}
