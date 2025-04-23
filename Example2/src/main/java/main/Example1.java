package main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example1 {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Vehicle vehicle =  context.getBean(Vehicle.class);

        System.out.printf("Vehicle name : %s \nVehicle hello: %s \n", vehicle.getName(),vehicle.hello());

        context.close(); // Vehicle sınıfının destroy metodu çalışır.
    }
}

/*
@Component : bir sınıfı Spring Bean olarak tanımlar. Yani Spring’e "Bu sınıfın bir nesnesini oluştur ve yönet" demek olur. Bu sınıf, Spring uygulaması başlarken IoC container tarafından oluşturulur ve bir bean olarak kaydedilir.

@ComponentScan : Spring’e hangi paketleri taraması gerektiğini söyler. Taramak demek: @Component, @Service, @Repository, @Controller gibi anotasyonlu sınıfları arayıp bulmak demek.

 */
