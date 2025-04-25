package com.example.beans;

import org.springframework.stereotype.Component;

// Beanlerin default davranışı singleton'dır.
@Component
public class Vehicle {
    private String name;
    private VehicleService vehicleService;

    public Vehicle() {
        System.out.println("Vehicle created");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String hello() {
        return "Hello from Vehicle";
    }
    public String toString() {
        return this.name;
    }
}

//Eğer Vehicle Singleton ve VehicleService Prototype ise:
//Vehicle bean'i Singleton olduğu için tek bir örnek oluşturulacaktır.

//VehicleService bean'i Prototype olduğu için ilk defa Vehicle bean'inin oluşturulması sırasında yeni bir VehicleService örneği oluşturulacak ve bu örnek sonraki kullanımlarda aynı kalacaktır. (mantıken singleton gibi davranır)

//Vehicle ve VehicleService Prototype Olsa:
//Vehicle ve VehicleService her ikisi de Prototype scope'ta ise, her iki bean de her defasında yeni bir örnek oluşturur.
//
//Yani, her seferinde farklı Vehicle ve VehicleService nesneleri üretilir.


