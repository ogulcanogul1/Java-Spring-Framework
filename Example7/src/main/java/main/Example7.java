package main;

import com.example.beans.Person;
import com.example.beans.VehicleService;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example7 {
    public static void main(String[] args) {

       var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

       Person person = context.getBean(Person.class);

        System.out.println(person);

        System.out.println("-".repeat(40));

        VehicleService vehicleService1 = context.getBean(VehicleService.class);
        VehicleService vehicleService2 = context.getBean(VehicleService.class);

        System.out.println(vehicleService1.hashCode());
        System.out.println(vehicleService2.hashCode());

        if(!vehicleService1.equals(vehicleService2)) {
            System.out.println("VehicleService beans are different (prototype)");
        } else {
            System.out.println("VehicleService beans are the same (singleton)");
        }



    }
}



