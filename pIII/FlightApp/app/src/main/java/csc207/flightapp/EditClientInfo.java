package csc207.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import backend.FileDatabase;
import backend.User;

public class EditClientInfo extends AppCompatActivity {
    User user =null;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client_info);
        Intent intent = getIntent();

        email = intent.getStringExtra("EMAIL");
        user = FileDatabase.getInstance().getUserManager().getUserWithEmail(email);

        //Sets the default values for all views
        EditText firstName = (EditText)findViewById(R.id.first_name);
        EditText lastName = (EditText)findViewById(R.id.last_name);
        EditText creditNum = (EditText)findViewById(R.id.credit_card_num);
        EditText expiryDate = (EditText)findViewById(R.id.expiry_date);
        TextView username = (TextView) findViewById(R.id.username);
        username.setText(email);
    }
}
