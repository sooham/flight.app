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
        //Intent intent = getIntent();
        //List<Itinerary> itineraries = FLIGHTS.getItineraries(intent.getStringExtra("ORIGIN"),
                //intent.getStringExtra("DESTINATION"),
               // intent.getStringExtra("DEPARTURE_DATE"));
       createTable(25);
    }

    /**
     * Creates the table for displaying all the searched itineraries.
     *
     * @param flights takes the key for what flights to be displayed.
     */
    private void createTable(List<Flight> flights){
        for (int x = 0; x<flights.size();x++) {
            // Creation row
            final TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

            // Creation textView that displays the all the Airline for each flight.
            final TextView flightText = new TextView(this);
            flightText.setText(flights.get(x).getAirline());
            flightText.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            // Creation of the textview that displays the flight number.
            flightText.setText(Objects.toString(flights.get(x).getNumber(), null));
            flightText.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            // Creation of the textview that displays the flight number.
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

    private void createTable(int value){
        for (int x = 0; x<value;x++) {
            // Creation row
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

            // Creation textView that displays the all the flghts.
            TextView flightText = new TextView(this);
            flightText.setText(Objects.toString(x));
            flightText.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.addView(flightText);

            // Creation of the textview that displays the duration.
            TextView flightText2 = new TextView(this);
            flightText2.setText(Objects.toString(x));;
            flightText2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.addView(flightText2);

            // Creation of the textView that displays the price of the trip.
            TextView flightText3 = new TextView(this);
            flightText3.setText(Objects.toString(x));
            flightText3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.addView(flightText3);

            //adds the row to the table
            table.addView(tableRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}
