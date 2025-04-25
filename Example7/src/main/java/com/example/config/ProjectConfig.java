package com.example.config;

// third way

import com.example.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

// third way
@Configuration
@ComponentScan("com.example.beans")
public class ProjectConfig {
    @Bean
    public Vehicle vehicle1()
    {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Mercedes");
        return vehicle;
    }

    @Bean
    public Vehicle vehicle2()
    {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("BMW");
        return vehicle;
    }

    @Primary
    @Bean
    public Vehicle vehicle3()
    {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Audi");
        return vehicle;
    }
}







