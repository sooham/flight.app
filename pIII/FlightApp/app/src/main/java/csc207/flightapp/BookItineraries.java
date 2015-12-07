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

import backend.Flight;
import backend.Itinerary;

public class BookItineraries extends Activity implements View.OnClickListener{
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_itineraries);
        table = (TableLayout)findViewById(R.id.flight_table);
        Intent intent = getIntent();
    }

    private void createTable(ArrayList<Itinerary> flights) {
        for (Itinerary itinerary : flights) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));

            // Creation textView that displays the all the flight airline.
            TextView flightText = new TextView(this);
            flightText.setText(itinerary.getFlights().size());
            flightText.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            flightText.setPadding(5, 5, 5, 5);
            tableRow.addView(flightText);

            // Creation of the textview that displays the number
            TextView flightText2 = new TextView(this);
            flightText2.setText(Objects.toString(itinerary.getDuration()));
            flightText2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            flightText2.setPadding(5, 5, 5, 5);
            tableRow.addView(flightText2);

            // Creation of the textView that displays the price of the trip.
            TextView flightText3 = new TextView(this);
            flightText3.setText(Objects.toString(itinerary.getPrice()));
            flightText3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tableRow.addView(flightText3);

            //button for view the flights in the itinerary
            MyButton viewFlights = new MyButton(this);
            viewFlights.itinerary = itinerary;
            viewFlights.setOnClickListener(this);
            tableRow.addView(viewFlights, 250,100);

            // Button for booking the itinerary


            //adds the row to the table
            table.addView(tableRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public void onClick(View view){


    }

        public class MyButton extends Button {
            public Itinerary itinerary;

            public MyButton(Context c){
                super(c);
            }
        }

}
