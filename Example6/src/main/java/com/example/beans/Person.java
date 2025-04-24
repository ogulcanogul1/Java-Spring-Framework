package com.example.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// 3. way
@Component("personBean")
public class Person {
    private String name;
    private int age;

    private final Vehicle vehicle;



    @Autowired
    public Person(@Qualifier("vehicle3") Vehicle vehicle)
    {
        this.name = "Ali";
        this.age = 30;
        this.vehicle = vehicle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

//    public void setVehicle(Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }
}




