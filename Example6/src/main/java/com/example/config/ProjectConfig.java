package com.example.config;

// third way

import com.example.beans.Speakers.BoseSpeakers;
import com.example.beans.Speakers.SonySpeakers;
import com.example.beans.Vehicle;
import com.example.beans.VehicleService;
import com.example.beans.tyres.BridgeStoneTyres;
import com.example.beans.tyres.MichelinTyres;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// third way
@Configuration
@ComponentScan("com.example.beans")
public class ProjectConfig {
    @Bean
    public Vehicle vehicle1(@Qualifier("mercedesVehicleService") VehicleService vehicleService)
    {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Mercedes");
        vehicle.setColor("Gray");
        vehicle.setModel("A200 AMG");
        vehicle.setYear("2023");
        vehicle.setVehicleService(vehicleService);
        return vehicle;
    }
    @Bean
    public Vehicle vehicle2(@Qualifier("bmwVehicleService") VehicleService vehicleService)
    {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("BMW");
        vehicle.setColor("White");
        vehicle.setModel("X5");
        vehicle.setYear("2022");
        vehicle.setVehicleService(vehicleService);
        return vehicle;
    }

    @Bean
    public Vehicle vehicle3(@Qualifier("audiVehicleService") VehicleService vehicleService)
    {

        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Audi");
        vehicle.setColor("Black");
        vehicle.setModel("A6");
        vehicle.setYear("2021");
        vehicle.setVehicleService(vehicleService);
        return vehicle;
    }

    @Bean
    public VehicleService bmwVehicleService(){
        VehicleService vehicleService = new VehicleService();
        vehicleService.setSpeaker(new BoseSpeakers());
        vehicleService.setTyre(new MichelinTyres());
        return vehicleService;
    }
    @Bean
    public VehicleService audiVehicleService(){
        VehicleService vehicleService = new VehicleService();
        vehicleService.setSpeaker(new SonySpeakers());
        vehicleService.setTyre(new MichelinTyres());
        return vehicleService;
    }
    @Bean
    public VehicleService mercedesVehicleService(){
        VehicleService vehicleService = new VehicleService();
        vehicleService.setSpeaker(new SonySpeakers());
        vehicleService.setTyre(new BridgeStoneTyres());
        return vehicleService;
    }


}







