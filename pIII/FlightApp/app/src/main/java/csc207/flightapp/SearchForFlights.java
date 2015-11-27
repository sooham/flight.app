package csc207.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SearchForFlights extends AppCompatActivity {
    EditText origin;
    EditText destination;
    EditText departureDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_flights);
        Intent intent = getIntent();

        //Displays the user's email on the top of the app.
        String email = intent.getStringExtra("EMAIL");
        TextView textView = (TextView)findViewById(R.id.display_email);
        textView.setText(email);
    }

    /**
     * Will pass the user input to an activity that finds all the flights.
     * @param view the current view of the app.
     */
    public void viewFlights(View view){
        origin = (EditText)findViewById(R.id.origin);
        destination = (EditText)findViewById(R.id.destination);
        departureDate = (EditText)findViewById(R.id.departure_date);
        Intent intent = new Intent(this, SearchForFlights.class);

        //passes the input information to the view_view_searched_flights.
        intent.putExtra("ORIGIN", origin.getText().toString());
        intent.putExtra("DESTINATION", destination.getText().toString());
        intent.putExtra("DEPARTURE_DATE", departureDate.getText().toString());
        startActivity(intent);
    }
}
