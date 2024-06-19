package com.example.atiluztranspo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboard extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DriverAdapter adapter;
    private List<Driver> driverList;

    private Button buttonAdd, buttonDelete, buttonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        recyclerView = findViewById(R.id.recyclerViewDrivers);
        buttonAdd = findViewById(R.id.buttonAddDriver);
        buttonDelete = findViewById(R.id.buttonDeleteDriver);
        buttonEdit = findViewById(R.id.buttonEditDriver);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        driverList = new ArrayList<>();
        adapter = new DriverAdapter(this, driverList);
        recyclerView.setAdapter(adapter);

        // Mock data (replace with actual database retrieval)
        driverList.add(new Driver("John Doe", "ABC123", "Toyota", "Blue"));
        driverList.add(new Driver("Jane Smith", "XYZ456", "Honda", "Red"));

        // Notify adapter about the data change
        adapter.notifyDataSetChanged();

        // Example click listeners for buttons (implement actual functionality)
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement add functionality
                Toast.makeText(AdminDashboard.this, "Add driver clicked", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement delete functionality
                Toast.makeText(AdminDashboard.this, "Delete driver clicked", Toast.LENGTH_SHORT).show();
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement edit functionality
                Toast.makeText(AdminDashboard.this, "Edit driver clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
