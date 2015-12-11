package csc207.flightapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import backend.FileDatabase;
import backend.UserManager;

public class SearchForClients extends Activity {
    UserManager userManager;
    EditText clientEmail;
    TextView loginFailure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_clients);
        userManager = FileDatabase.getInstance().getUserManager();
    }

    /**
     * Checks if the email for the client exists if it does will create an
     * intent and go to the corresponding activity given by previous
     * activity's instructions.
     * Otherwise will display a message.
     * @param view the view of the button clicked
     */
    public void editClientInfo(View view) {
        // get the email from the edit text
        clientEmail = (EditText) findViewById(R.id.get_client_email);
        String email = clientEmail.getText().toString();

        if (userManager.getUserWithEmail(email)!= null) {
            // get the calling intent's instructions
            Intent intent = getIntent();
            Intent transitionIntent;

            if (intent.getBooleanExtra(AdminMenu.EDIT_CLIENT, false)) {
                transitionIntent = new Intent(this, EditClientInfo.class);
            } else {
                transitionIntent = new Intent(this, SearchForFlights.class);
            }

            transitionIntent.putExtra(UserLogin.EMAIL, email);
            startActivity(transitionIntent);
        } else{
            loginFailure = (TextView) findViewById(R.id.client_failure);
            loginFailure.setText("Client email is incorrect.");
        }


    }
}
