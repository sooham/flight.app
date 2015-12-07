package csc207.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import backend.FileDatabase;


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
        Intent intent = new Intent(this, ViewSearchedFlights.class);
        FileDatabase.getInstance().addFlightFromFile("/data/flights1.txt");
        try {
            FileDatabase.getInstance().serializeManagers(this.getApplicationContext().getFilesDir().getCanonicalPath() + "/");
        }catch(IOException e){

        }

        //passes the input information to the view_view_searched_flights.
        intent.putExtra("Flights",FileDatabase.getInstance().getFlightManger().getFlights(origin.getText().toString(),
                destination.getText().toString(), departureDate.getText().toString()));
        startActivity(intent);
    }

    public void viewItineraries(View view){
        origin = (EditText)findViewById(R.id.origin);
        destination = (EditText)findViewById(R.id.destination);
        departureDate = (EditText)findViewById(R.id.departure_date);
        Intent intent = new Intent(this, BookItineraries.class);

        //passes the input information to the view_view_searched_flights.
        try {
            FileDatabase.getInstance().serializeManagers(this.getApplicationContext().getFilesDir().getCanonicalPath() + "/");
        }catch(IOException e){

        }
        intent.putExtra("Flights",FileDatabase.getInstance().getFlightManger().getFlights(origin.getText().toString(),
               destination.getText().toString(), departureDate.getText().toString()));
       startActivity(intent);

    }
}

