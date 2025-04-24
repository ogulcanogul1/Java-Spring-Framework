package com.example.config;

// third way

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// third way
@Configuration
@ComponentScan("com.example.beans")
public class ProjectConfig {

}



//@Configuration
//public class ProjectConfig {
//
//    @Bean
//    public Vehicle vehicle()
//    {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setName("Mercedes");
//        return vehicle;
//    }
//    // first way
////    @Bean
////    public Person person()
////    {
////        Person person = new Person();
////        person.setName("Ali");
////        person.setAge(30);
////        person.setVehicle(vehicle());
////        return person;
////    }
//
//    // second way
//    @Bean
//    public Person person(Vehicle vehicle){
//        Person person = new Person();
//        person.setName("Ali");
//        person.setAge(30);
//        person.setVehicle(vehicle); // vehicle() methodu çağrıldığında vehicle bean'i oluşturulur.
//        return person;
//    }
//
//}



