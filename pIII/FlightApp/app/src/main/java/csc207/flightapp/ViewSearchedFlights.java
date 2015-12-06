package csc207.flightapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import backend.*;

public class ViewSearchedFlights extends AppCompatActivity implements View.OnClickListener{
    private FlightManager FLIGHTS = null;
    private TableLayout table;
    private Flight bookFlight;
    private User user;

    /**
     * Creates the activity for viewing flights.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_searched_flights);
        try {
            FileDatabase.getInstance().addFlightFromFile(this.getApplicationContext().getFilesDir().getCanonicalPath() + "/flights1.txt");
        }catch(IOException c){

        }
        FLIGHTS = FileDatabase.getInstance().getFlightManger();
        table = (TableLayout)findViewById(R.id.flight_table);
        Intent intent = getIntent();
        user = FileDatabase.getInstance().getUserManager().getUserWithEmail(
                intent.getStringExtra("EMAIL"));
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
            flightText.setPadding(5,5,5,5);
            tableRow.addView(flightText);

            // Creation of the textview that displays the number
            TextView flightText2 = new TextView(this);
            flightText2.setText(Objects.toString(flight.getNumber()));;
            flightText2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            flightText2.setPadding(5,5,5,5);
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
            flightText4.setPadding(5,5,5,5);
            tableRow.addView(flightText4);

            /*Creation of the textView that displays the num available seats.
            TextView flightText5 = new TextView(this);
            flightText5.setText(Objects.toString(flight.getNumEmptySeats()));
            flightText5.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            flightText5.setPadding(5,5,5,5);
            tableRow.addView(flightText5);
            */

            //Adds a button to book the flight
            MyButton button = new MyButton(this);
            button.setText("Book");
            button.setTextSize(8);
            button.flight = flight;
            button.callOnClick();
            tableRow.addView(button, 300, 100);

            //adds the row to the table
            table.addView(tableRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public void onClick(View view){
        user.bookItinerary(((MyButton)view).flight);

    }

    //constructor for a custom button.
    public class MyButton extends Button{
        public Flight flight;
        public MyButton(Context c){
            super(c);
        }

    }

}
