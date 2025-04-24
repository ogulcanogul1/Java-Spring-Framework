package main;

import com.example.beans.Person;
import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example6 {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Person person = context.getBean(Person.class);

        Vehicle vehicleOfPerson = person.getVehicle();
        System.out.println(vehicleOfPerson.toString());

        System.out.println("---------------------------------------------------");
        System.out.println(vehicleOfPerson.getVehicleService().getSpeaker().getSpeakerBrand());
        System.out.println("---------------------------------------------------");
        System.out.println(vehicleOfPerson.getVehicleService().getTyre().getTyreBrand());

        System.out.println("---------------------------------------------------");

        String[] lists = context.getBeanNamesForType(Vehicle.class);

        for (String element : lists)
        {
            System.out.println(element);
        }

    }
}



