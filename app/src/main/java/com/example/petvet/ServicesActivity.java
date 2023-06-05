package com.example.petvet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ServicesActivity extends AppCompatActivity {

    private Button medicalButton;
    private Button groomingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        medicalButton = findViewById(R.id.medical_button);
        groomingButton = findViewById(R.id.grooming_button);

        medicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServicesActivity.this, ReservationFormActivity.class);
                startActivity(intent);
            }
        });

        groomingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServicesActivity.this, GroomingReservationForm.class);
                startActivity(intent);
            }
        });
    }
}
