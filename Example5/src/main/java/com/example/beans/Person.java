package com.example.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// 3. way
@Component
public class Person {
    private String name;
    private int age;

    private final Vehicle vehicle;


    @Autowired
    public Person(@Qualifier("vehicle2") Vehicle vehicle) {
        this.vehicle = vehicle;
        this.name = "Ali";
        this.age = 30;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", vehicle=" + vehicle +
                '}';
    }
}




