package main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

public class Example3 {
    public static void main(String[] args) {

        // koşula göre bean ekleme
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setName("Audi");
        Supplier<Vehicle> audiSupplier = () -> vehicle1;

        Supplier<Vehicle> mercedesSupplier = () -> {
            Vehicle vehicle2 = new Vehicle();
            vehicle2.setName("Mercedes");
            return vehicle2;
        };

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        int number = 4;


        if(number % 2 == 0) {
            context.registerBean("Audi", Vehicle.class, audiSupplier);
        } else {
            context.registerBean("Mercedes", Vehicle.class, mercedesSupplier);
        }

        Vehicle car = null;
        try {
               car =  context.getBean("Audi", Vehicle.class);
        } catch (NoSuchBeanDefinitionException exception)
        {
            System.out.println(exception.getMessage());
        }

        try {
            car = context.getBean("Mercedes", Vehicle.class);
        } catch (NoSuchBeanDefinitionException exception)
        {
            System.out.println(exception.getMessage());
        }

        if (car != null)
            System.out.println("The car is : " + car.getName());



    }
}



