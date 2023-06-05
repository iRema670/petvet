package com.example.petvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Appointment extends AppCompatActivity {

    private TextView appointmentDetailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        appointmentDetailsTextView = findViewById(R.id.appointment_details_textview);

        // Get the selected doctor, date, and time from the intent
        Intent intent = getIntent();
        String selectedDoctor = intent.getStringExtra("doctor");
        String selectedDate = intent.getStringExtra("date");
        String selectedTime = intent.getStringExtra("time");

        // Set the text of the appointment details TextView
        String appointmentDetails = "Here are your appointment details:\nDoctor: " + selectedDoctor + "\nDate: " + selectedDate + "\nTime: " + selectedTime;
        appointmentDetailsTextView.setText(appointmentDetails);
    }
}