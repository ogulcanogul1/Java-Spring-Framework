package com.example.config;

import com.example.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
//“Bu sınıf içinde @Bean ile işaretlenmiş olan metotların her biri, Spring konteynerine bir bean tanımlar.”
public class ProjectConfig {

    @Bean(name = "AudiVehicle")
    Vehicle vehicle()
    {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Audi A3");
        return vehicle;
    }

    @Bean(value = "BMWVehicle")
    Vehicle vehicle2()
    {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("BMW 3 Series");
        return vehicle;
    }

    @Primary
    @Bean("MercedesVehicle")
    Vehicle vehicle3()
    {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Mercedes C Class");
        return vehicle;
    }

    @Bean
    String hello()
    {
        return "hello world";
    }

    @Bean
    Integer number()
    {
        return 32;
    }
}

/*
  @Bean(name = "AudiA3"),  @Bean(value = "AudiA3") ,   @Bean("AudiA3")
  bu üç kullanımda aynıdır bean ismini belirler.
* */