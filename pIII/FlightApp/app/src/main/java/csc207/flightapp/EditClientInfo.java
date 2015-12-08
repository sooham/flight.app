package csc207.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import backend.FileDatabase;
import backend.User;
import backend.UserManager;

public class EditClientInfo extends AppCompatActivity {

    User user;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd");

    // The views in the activity
    EditText firstName;
    EditText lastName;
    EditText creditNum;
    EditText expiryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client_info);

        // get the email of the logged in user
        Intent intent = getIntent();
        user = FileDatabase.getInstance().getUserManager().getUserWithEmail(
                intent.getStringExtra(UserLogin.EMAIL));

        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        creditNum = (EditText) findViewById(R.id.credit_card_num);
        expiryDate = (EditText) findViewById(R.id.expiry_date);

        // Sets the default values for all views at login
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        creditNum.setText(Integer.toString(user.getCreditCardNumber()));
        expiryDate.setText(dateFormatter.format(user.getExpiryDate()));
        }

    /**
     * Saves the changes made to the fields. Called by the save button.
     *
     * @param view the save button view.
     */
    public void saveChanges(View view) {
        try {
            Date newDate = dateFormatter.parse(expiryDate.getText().toString());
            user.setExpiryDate(newDate);
            user.setFirstName(firstName.getText().toString());
            user.setLastName(lastName.getText().toString());
            user.setCreditCardNumber(
            Integer.parseInt(creditNum.getText().toString()));
        } catch (ParseException e) {}

        //Saves the changes made to the user and the UserManager
        try{
            FileDatabase.getInstance().serializeManagers(
                    this.getApplicationContext().getFilesDir(
                    ).getCanonicalPath()+"/");
        } catch(IOException e) {}

        finish();
    }

}
