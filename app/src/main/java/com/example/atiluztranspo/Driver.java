package com.example.atiluztranspo;

public class Driver {
    private String fullName;
    private String plateNumber;
    private String model;
    private String color;

    public Driver(String fullName, String plateNumber, String model, String color) {
        this.fullName = fullName;
        this.plateNumber = plateNumber;
        this.model = model;
        this.color = color;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }
}
