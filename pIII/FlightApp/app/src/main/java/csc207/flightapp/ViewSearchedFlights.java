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
    private FlightManager FLIGHTS = null;
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
        FileDatabase.getInstance().addFlightFromFile("/data/flights1.txt");
        FLIGHTS = FileDatabase.getInstance().getFlightManger();
        table = (TableLayout)findViewById(R.id.flight_table);
        Intent intent = getIntent();
        List<Flight> flights = FLIGHTS.getFlights(intent.getStringExtra("ORIGIN"),
                intent.getStringExtra("DESTINATION"),
               intent.getStringExtra("DEPARTURE_DATE"));
        createTable(flights);
    }

    /**
     * Creates the table for displaying all the searched itineraries.
     *
     * @param flights takes the key for what flights to be displayed.
     */
    private void createTable(List<Flight> flights){
        for (Flight flight:flights) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));

            // Creation textView that displays the all the flight airline.
            TextView flightText = new TextView(this);
            flightText.setText(flight.getAirline());
            flightText.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tableRow.addView(flightText);

            // Creation of the textview that displays the number
            TextView flightText2 = new TextView(this);
            flightText2.setText(Objects.toString(flight.getNumber()));;
            flightText2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tableRow.addView(flightText2);

            // Creation of the textView that displays the duration of the trip.
            TextView flightText3 = new TextView(this);
            flightText3.setText(Objects.toString(flight.getDuration()));
            flightText3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tableRow.addView(flightText3);

            // Creation of the textView that displays the price of the trip.
            TextView flightText4 = new TextView(this);
            flightText4.setText(Objects.toString(flight.getPrice()));
            flightText4.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tableRow.addView(flightText4);

            // Creation of the textView that displays the num available seats.
            TextView flightText5 = new TextView(this);
            flightText5.setText(Objects.toString(flight.getNumEmptySeats()));
            flightText5.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tableRow.addView(flightText5);

            //adds the row to the table
            table.addView(tableRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
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
