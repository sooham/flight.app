package csc207.flightapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import backend.FileDatabase;
import backend.Flight;
import backend.FlightManager;
import backend.Itinerary;
import backend.User;

public class BookItineraries extends Activity implements View.OnClickListener{
    private FlightManager flightManager = null;
    private TableLayout table;
    private Flight bookFlight;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_itineraries);
        flightManager = FileDatabase.getInstance().getFlightManger();
        table = (TableLayout)findViewById(R.id.flight_table);
        Intent intent = getIntent();
        user = FileDatabase.getInstance().getUserManager().getUserWithEmail(
                intent.getStringExtra("EMAIL"));
        createTable((ArrayList<Itinerary>) intent.getSerializableExtra("Flights"));
    }

    /**
     * Creates the table for displaying all the searched itineraries.
     *
     * @param itineraries takes the key for what flights to be displayed.
     */
    private void createTable(ArrayList<Itinerary> itineraries){
        for (Itinerary flight:itineraries) {
            System.out.print(flight.getOrigin());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));

            // Creation of the textview that displays the number of transfers
            TextView flightText2 = new TextView(this);
            flightText2.setText(Objects.toString(flight.getFlights().size()));;
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
            flightText4.setPadding(5, 5, 5, 5);
            tableRow.addView(flightText4);


            //Adds a button to view the flights in the itinerary
            viewButton view = new viewButton(this);
            view.setText("View");
            view.setTextSize(8);
            view.flight = flight;
            view.setOnClickListener(this);
            tableRow.addView(view, 100, 100);

            //Adds a button to book the flight
            bookButton book = new bookButton(this);
            book.setText("Book");
            book.setTextSize(8);
            book.flight = flight;
            book.setOnClickListener(this);
            tableRow.addView(book, 100, 100);

            //adds the row to the table
            table.addView(tableRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public void onClick(View view){
        switch(((viewButton)view).getType()){
            case "View Button":
                Intent intent = new Intent(this, ViewSearchedFlights.class);
                intent.putExtra("Flights", ((viewButton)view).flight.getFlights());
                startActivity(intent);
                break;
            case "Book Button":
                user.bookItinerary(((bookButton)view).flight);
                break;

        }

    }

    //constructor for a custom button.
    public class viewButton extends Button {
        public Itinerary flight;

        public viewButton(Context c) {
            super(c);
        }

        /**
         * Returns a string that represetns the type of custom button.
         * Mainly used for switch statement.
         * @return
         */
        public String getType(){
            return "View Button";
        }

    }

    public class bookButton extends viewButton {
        public Itinerary flight;

        public bookButton(Context c){
            super(c);
        }

        /**
         * Returns a string that represetns the type of custom button.
         * Mainly used for switch statement.
         * @return
         */
        public String getType(){
            return "Book Button";
        }

    }

}
