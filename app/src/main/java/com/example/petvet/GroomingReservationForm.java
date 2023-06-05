package com.example.petvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class GroomingReservationForm extends AppCompatActivity {

    private Spinner groomerSpinner;
    private Spinner dateSpinner;
    private Spinner timeSpinner;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grooming_reservation_form);

        groomerSpinner = findViewById(R.id.Groomer_Spinner);
        dateSpinner = findViewById(R.id.date_spinner);
        timeSpinner = findViewById(R.id.time_spinner);
        confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setEnabled(false); // Disable the confirm button initially

        // Populate the groomer spinner with a list of groomers
        List<String> groomerList = Arrays.asList(getResources().getStringArray(R.array.groomer_list));
        ArrayAdapter<String> groomerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, groomerList);
        groomerSpinner.setAdapter(groomerAdapter);

        // Populate the date spinner with a list of available dates
        List<String> dateList = Arrays.asList(getResources().getStringArray(R.array.grooming_date_list));
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dateList);
        dateSpinner.setAdapter(dateAdapter);

        // Populate the time spinner with a list of available times
        List<String> timeList = Arrays.asList(getResources().getStringArray(R.array.grooming_time_list));
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, timeList);
        timeSpinner.setAdapter(timeAdapter);

        // Set an OnItemSelectedListener on each spinner to check if the confirm button should be enabled or disabled
        groomerSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener());
        dateSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener());
        timeSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener());

        // Set an OnClickListener on the confirm button
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected groomer, date, and time from the spinners
                String selectedGroomer = groomerSpinner.getSelectedItem().toString();
                String selectedDate = dateSpinner.getSelectedItem().toString();
                String selectedTime = timeSpinner.getSelectedItem().toString();

                // Launch the grooming appointment activity with the selected values
                Intent intent = new Intent(GroomingReservationForm.this, GroomingAppointment.class);
                intent.putExtra("groomer", selectedGroomer);
                intent.putExtra("date", selectedDate);
                intent.putExtra("time", selectedTime);
                startActivity(intent);
            }
        });
    }

    // Custom SpinnerItemSelectedListener class to check if the confirm button should be enabled or disabled
    private class SpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // Check if the user has made a selection from all three spinners
            if (groomerSpinner.getSelectedItemPosition() != 0 &&
                    dateSpinner.getSelectedItemPosition() != 0 &&
                    timeSpinner.getSelectedItemPosition() != 0) {
                // Enable the confirm button
                confirmButton.setEnabled(true);
            } else {
                // Disable the confirm button
                confirmButton.setEnabled(false);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing
        }
    }
}