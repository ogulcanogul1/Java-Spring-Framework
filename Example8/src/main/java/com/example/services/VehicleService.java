package com.example.services;


import org.springframework.stereotype.Component;

@Component
public class VehicleService {
    private String brand;
    private String model;
    private int year;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void makeSound(boolean isStarted){

        if(isStarted) {
            System.out.println("Vroom Vroom");
        } else {
            System.out.println("Silence");
        }
    }

    public void powerCalculation(Integer value1, Integer value2) throws  Exception
    {
        if(value2 == 0)
        {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        System.out.println("Power Calculation: " + (value1 / value2));

    }

}
