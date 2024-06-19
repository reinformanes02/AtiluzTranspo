package com.example.atiluztranspo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(view -> {
            // Close the current activity
            finishAffinity(); // This will close all activities and exit the application
            // System.exit(0); // Optional: this can be used to forcefully exit the app
        });

        Spinner roleSpinner = findViewById(R.id.roleSpinner);
        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRole = parent.getItemAtPosition(position).toString();
                switch (selectedRole) {
                    case "Driver":
                        startActivity(new Intent(Start.this, DriverLogs.class));
                        break;
                    case "Passenger":
                        startActivity(new Intent(Start.this, MainActivity.class));
                        break;
                    case "Admin":
                        startActivity(new Intent(Start.this, Admin.class));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }
}
