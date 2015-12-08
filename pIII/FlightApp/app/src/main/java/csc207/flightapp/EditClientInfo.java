package csc207.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import backend.FileDatabase;
import backend.User;

public class EditClientInfo extends AppCompatActivity {
    User user =null;
    String email;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd");

    //The views in the activity
    EditText firstName;
    EditText lastName;
    EditText creditNum;
    EditText expiryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client_info);
        Intent intent = getIntent();

        email = intent.getStringExtra(UserLogin.EMAIL);
        user = FileDatabase.getInstance().getUserManager().getUserWithEmail(email);

        //Sets the default values for all views
        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        creditNum = (EditText)findViewById(R.id.credit_card_num);
        expiryDate = (EditText)findViewById(R.id.expiry_date);
        if(user.getFirstName()!=null) {
            firstName.setText(user.getFirstName());
        }
        if(user.getLastName() !=null) {
            lastName.setText(user.getLastName());
        }
        if(user.getCreditCardNumber() != 0) {
            creditNum.setText(Objects.toString(user.getCreditCardNumber()));
        }
        if (user.getExpiryDate()!=null) {
            expiryDate.setText(dateFormatter.format(user.getExpiryDate()));
        }

    }

    public void saveChanges(View view){
        user.setFirstName(firstName.getText().toString());
        user.setLastName(lastName.getText().toString());
        user.setCreditCardNumber(Integer.parseInt(creditNum.getText().toString()));
        try{
            Date newDate = dateFormatter.parse(expiryDate.getText().toString());
            user.setExpiryDate(newDate);
        }catch(ParseException e){

        }

        //Saves the changes made to the user and the UserManager
        try{
            FileDatabase.getInstance().serializeManagers(
                    this.getApplicationContext().getFilesDir().getCanonicalPath()+"/");
        }catch(IOException e){

        }
        this.finish();
    }

}
