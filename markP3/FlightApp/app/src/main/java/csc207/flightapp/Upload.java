package csc207.flightapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import backend.FileDatabase;

public class Upload extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
    }

    /**
     * Uploads the file in the EditText once the Upload button is pressed.
     *
     * @param view the button view pressed
     */
    public void upload(View view) {
        // check if the radio group toggle is set to Flight or Client
        RadioButton flightsRadioBtn = (RadioButton) findViewById(R.id.
                                                                flight_csv);

        // get the path to file
        EditText csvField = (EditText) findViewById(R.id.csv_filename);

        String csvFileName = csvField.getText().toString();

        try {
            String fullPath = this.getApplicationContext().getFilesDir(
            ).getCanonicalPath() + "/" + csvFileName;

            // throw error if does not file exists
            if (!(new File(fullPath).exists())) {
                throw new IOException();
            }

            if (flightsRadioBtn.isChecked()) {
                // upload as flights
                FileDatabase.getInstance().addFlightFromFile(fullPath);
            } else { // upload as clients
                FileDatabase.getInstance().addClientFromFile(fullPath);
            }

            // autosave
            FileDatabase.getInstance().serializeManagers(
                this.getApplicationContext().getFilesDir().getCanonicalPath()
                        + "/"
            );

            finish();
        } catch (IOException e) {
            TextView failureText = (TextView) findViewById(R.id
                    .csv_upload_failure);
            failureText.setText("Filename is incorrect");
        }
    }
}
