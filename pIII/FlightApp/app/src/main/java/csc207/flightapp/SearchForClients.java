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
     * Checks if the email for the client exists if it does will create an intent and go
     * to the editClientInfo activity. Otherwise will display a message.
     * @param view the view of the button clicked
     */
    public void editClientInfo(View view) {
        clientEmail = (EditText) findViewById(R.id.get_client_email);
        String email = clientEmail.getText().toString();
        if(userManager.getUserWithEmail(email)!= null){
            Intent intent = new Intent(this, EditClientInfo.class);
            intent.putExtra(UserLogin.EMAIL, email);
            startActivity(intent);
        }else{
            loginFailure = (TextView) findViewById(R.id.client_failure);
            loginFailure.setText("Client email is incorrect.");
        }


    }
}
