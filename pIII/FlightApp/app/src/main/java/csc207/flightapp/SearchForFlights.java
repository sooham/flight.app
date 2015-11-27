package csc207.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SearchForFlights extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_flights);
        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        TextView textView = (TextView)findViewById(R.id.display_email);
        textView.setText(email);


    }
}
