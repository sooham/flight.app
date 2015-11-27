package csc207.flightapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import backend.*;

public class ViewSearchedFlights extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_searched_flights);

    }

    /**
     * Creates the table for displaying all the searched itineraries.
     */
    private void createTable(){

    }
}
