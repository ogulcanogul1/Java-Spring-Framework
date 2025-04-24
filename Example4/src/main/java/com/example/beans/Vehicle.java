package com.example.beans;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {
    private String name = "Mercedes";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String hello() {
        return "Hello from Vehicle";
    }

    public Vehicle() {
        System.out.println("Vehicle created");
    }


    public String toString() {
        return this.name;
    }
}


