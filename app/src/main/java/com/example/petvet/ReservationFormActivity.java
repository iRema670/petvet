package com.example.petvet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservationFormActivity extends AppCompatActivity {

    private Spinner doctorSpinner;
    private Spinner dateSpinner;
    private Spinner timeSpinner;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);

        doctorSpinner = findViewById(R.id.doctor_spinner);
        dateSpinner = findViewById(R.id.date_spinner);
        timeSpinner = findViewById(R.id.time_spinner);
        confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setEnabled(false); // Disable the confirm button initially

        // Populate the doctor spinner with a list of doctors
        List<String> doctorList = Arrays.asList(getResources().getStringArray(R.array.doctor_list));
        ArrayAdapter<String> doctorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, doctorList);
        doctorSpinner.setAdapter(doctorAdapter);

        // Populate the date spinner with a list of available dates
        List<String> dateList = Arrays.asList(getResources().getStringArray(R.array.date_list));
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dateList);
        dateSpinner.setAdapter(dateAdapter);

        // Populate the time spinner with a list of available times
        List<String> timeList = Arrays.asList(getResources().getStringArray(R.array.time_list));
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, timeList);
        timeSpinner.setAdapter(timeAdapter);

        // Set an OnItemSelectedListener on each spinner to check if the confirm button should be enabled or disabled
        doctorSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener());
        dateSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener());
        timeSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener());

        // Set an OnClickListener on the confirm button
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected doctor, date, and time from the spinners
                String selectedDoctor = doctorSpinner.getSelectedItem().toString();
                String selectedDate = dateSpinner.getSelectedItem().toString();
                String selectedTime = timeSpinner.getSelectedItem().toString();

                // Launch the appointment activity with the selected values
                Intent intent = new Intent(ReservationFormActivity.this, Appointment.class);
                intent.putExtra("doctor", selectedDoctor);
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
            if (doctorSpinner.getSelectedItemPosition() != 0 &&
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