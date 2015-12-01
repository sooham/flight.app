package csc207.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import backend.FileDatabase;
import backend.UserManager;

public class UserLogin extends AppCompatActivity {
    EditText email;
    EditText password;
    UserManager user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            FileDatabase.createInstance(this.getApplicationContext().getFilesDir().getCanonicalPath()+"/");
            System.out.println(this.getApplicationContext().getFilesDir().getCanonicalPath());
        }catch(IOException e){

        }
        user = FileDatabase.getInstance().getUserManager();
        setContentView(R.layout.activity_user_login2);
    }

    /**
     * Calls the SearchForFlights activity if the user entered a valid email and password.
     * Passes the users email as a variable to use in the SearchForFlights activity.
     * @param view the view where the button is pressed.
     */
    public void searchFlightIntent(View view) {
        email = (EditText)findViewById(R.id.get_email);
        password = (EditText)findViewById(R.id.get_password);

        if (user.loginCredentialsCorrect(email.getText().toString(),password.getText().toString())){
            Intent intent = new Intent(this, SearchForFlights.class);
            intent.putExtra("EMAIL", email.getText().toString());
            startActivity(intent);
        }else {
            TextView textView = (TextView) findViewById(R.id.login_failure);
            textView.setText("Password or Username is incorrect.");
        }
    }

}
