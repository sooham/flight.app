package csc207.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import backend.FileDatabase;
import backend.UserManager;


public class SearchForFlights extends AppCompatActivity {
    EditText origin;
    EditText destination;
    EditText departureDate;
    String email;
    UserManager manger = null;

    /**
     * Method for creating the Intent for searching for flights.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manger = FileDatabase.getInstance().getUserManager();
        setContentView(R.layout.activity_search_for_flights);
        Intent intent = getIntent();

        //Displays the user's email on the top of the app.
        email = intent.getStringExtra("EMAIL");
        TextView textView = (TextView)findViewById(R.id.display_email);
        textView.setText(email);
    }

    /**
     * Will pass the user input to an activity that finds all the flights.
     * @param view the current view of the app.
     */
    public void viewFlights(View view){
        //Finds the Views containing important information about the flights
        origin = (EditText)findViewById(R.id.origin);
        destination = (EditText)findViewById(R.id.destination);
        departureDate = (EditText)findViewById(R.id.departure_date);
        Intent intent = new Intent(this, ViewSearchedFlights.class);

        //passes the input information to the view_view_searched_flights.
        intent.putExtra("ORIGIN", origin.getText().toString());
        intent.putExtra("DESTINATION", destination.getText().toString());
        intent.putExtra("DEPARTURE_DATE", departureDate.getText().toString());
        intent.putExtra("EMAIL", email);
        startActivity(intent);
    }

    public void viewItineraries(View view){

    }

    public void editClientInfo(View view){
        Intent intent = new Intent(this, EditClientInfo.class);
        intent.putExtra("EMAIL", email);
        startActivity(intent);
    }
}
