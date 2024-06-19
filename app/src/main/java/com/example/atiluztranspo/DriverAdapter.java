package com.example.atiluztranspo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {

    private List<Driver> drivers;
    private Context context;

    // Constructor
    public DriverAdapter(Context context, List<Driver> drivers) {
        this.context = context;
        this.drivers = drivers;
    }

    // ViewHolder class
    public class DriverViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFullName, textViewPlateNumber, textViewModel, textViewColor;

        public DriverViewHolder(View itemView) {
            super(itemView);
            textViewFullName = itemView.findViewById(R.id.textViewFullName);
            textViewPlateNumber = itemView.findViewById(R.id.textViewPlateNumber);
            textViewModel = itemView.findViewById(R.id.textViewModel);
            textViewColor = itemView.findViewById(R.id.textViewColor);
        }
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_driver, parent, false);
        return new DriverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        Driver driver = drivers.get(position);
        holder.textViewFullName.setText(driver.getFullName());
        holder.textViewPlateNumber.setText("Plate Number: " + driver.getPlateNumber());
        holder.textViewModel.setText("Model: " + driver.getModel());
        holder.textViewColor.setText("Color: " + driver.getColor());
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }
}
