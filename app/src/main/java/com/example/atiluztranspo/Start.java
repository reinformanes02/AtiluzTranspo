package com.example.atiluztranspo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(view -> {
            // Close the current activity
            finishAffinity(); // This will close all activities and exit the application
            // System.exit(0); // Optional: this can be used to forcefully exit the app
        });
    }
}