package csc207.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserLogin extends AppCompatActivity {
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login2);
    }
    public void searchFlightIntent(View view) {
        email = (EditText)findViewById(R.id.get_email);
        Intent intent = new Intent(this, SearchForFlights.class);
        intent.putExtra("EMAIL", email.getText().toString());
        startActivity(intent);
    }

}
