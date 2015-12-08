package csc207.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import backend.FileDatabase;
import backend.FlightManager;
import backend.Flight;
import backend.Itinerary;


public class SearchForFlights extends AppCompatActivity {

    private EditText origin;
    private EditText destination;
    private EditText departureDate;
    private String email;

    public static final String DISPLAY_RESULTS = "csc207.FlightApp" +
            ".DISPLAY_RESULTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_flights);

        // get the email
        Intent intent = getIntent();
        email = intent.getStringExtra(UserLogin.EMAIL);
    }

    /**
     * Calls the ViewSearchedFlights or ViewItineraries activities with the
     * inputs given to this Views EditTexts.
     *
     * @param view the current view of the app.
     */
    public void viewResults(View view) {

        origin = (EditText) findViewById(R.id.origin);
        destination = (EditText) findViewById(R.id.destination);
        departureDate = (EditText) findViewById(R.id.departure_date);
        RadioButton flightsBtn = (RadioButton) findViewById(R.id.flight_search);

        // Check if the RadioGroup Selection is Flights or Itineraries
        Intent intent;
        if (flightsBtn.isChecked()) {
            intent = new Intent(this, ViewSearchedFlights.class);
            // passes the input information to the correct Activity
            ArrayList<Flight> results = FlightManager.getInstance().getFlights(
                    origin.getText().toString(),
                    destination.getText().toString(),
                    departureDate.getText().toString()
            );
            intent.putExtra(DISPLAY_RESULTS, results);

        } else {
            intent = new Intent(this, BookItineraries.class);
            // passes the input information to the correct Activity
            ArrayList<Itinerary> results = FlightManager.getInstance().getItineraries(
                    origin.getText().toString(),
                    destination.getText().toString(),
                    departureDate.getText().toString()
            );
            intent.putExtra(DISPLAY_RESULTS, results);
            intent.putExtra(UserLogin.EMAIL, email);
            }

            // TODO:WTF is this doing in search Angad bro
        /*
        FileDatabase.getInstance().addFlightFromFile("/data/flights1.txt");
        try {
            FileDatabase.getInstance().serializeManagers(this.getApplicationContext().getFilesDir().getCanonicalPath() + "/");
        }catch(IOException e){

        }
        */
            // start the activity
            startActivity(intent);
        }

    public void editClientInfo(View view) {
        Intent intent = new Intent(this, EditClientInfo.class);
        intent.putExtra(UserLogin.EMAIL, email);
        startActivity(intent);

    }

    public void viewBookedItineraries(View view){
        Intent intent = new Intent(this, ViewBooked.class);
        intent.putExtra(UserLogin.EMAIL, email);
        startActivity(intent);
    }
}

