package csc207.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import backend.*;

public class ViewSearchedFlights extends AppCompatActivity {
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

        Intent intent = getIntent();

        // get ListView object
        ListView flightListView = (ListView) findViewById(R.id.flight_list);

        ArrayList<Flight> results = (ArrayList<Flight>)
                intent.getSerializableExtra(SearchForFlights.DISPLAY_RESULTS);

        ArrayAdapter<Flight> adapter = new ArrayAdapter<Flight>(this, R
                .layout.activity_view_searched_flights, R.id.flight_entry,
                results);

        flightListView.setAdapter(adapter);

    }
}
