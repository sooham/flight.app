package csc207.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import backend.*;

public class ViewSearchedFlights extends AppCompatActivity {
    private final static FlightManager FLIGHTS = FileDatabase.getFlightManger();
    private TableLayout table;

    /**
     * Creates the activity for viewing flights.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_searched_flights);
        table = (TableLayout)findViewById(R.id.flight_table);
        Intent intent = getIntent();
        List<Itinerary> itineraries = FLIGHTS.getItineraries(intent.getStringExtra("ORIGIN"),
                intent.getStringExtra("DESTINATION"),
                intent.getStringExtra("DEPARTURE_DATE"));
        createTable(itineraries);
    }

    /**
     * Creates the table for displaying all the searched itineraries.
     *
     * @param flights takes the key for what flights to be displayed.
     */
    private void createTable(List<Itinerary> flights){
        for (int x = 0; x<flights.size();x++){
            // Creation row
            final TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

            // Creation textView that displays the all the flghts.
            final TextView flightText = new TextView(this);
            flightText.setText(flights.get(x).getFlights().toString());
            flightText.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            // Creation of the textview that displays the duration.
            flightText.setText(Objects.toString(flights.get(x).getDuration(), null));
            flightText.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            // Creation of the textView that displays the price of the trip.
            flightText.setText(Objects.toString(flights.get(x).getPrice()));
            flightText.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            //adds the row to the table
            table.addView(tableRow);
        }



    }
}
