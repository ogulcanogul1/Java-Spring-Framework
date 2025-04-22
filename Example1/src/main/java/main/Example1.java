package main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example1 {
    public static void main(String[] args) {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setName("Honda City");

        System.out.println("non-spring : " + vehicle1.getName());

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
//        new AnnotationConfigApplicationContext(ProjectConfig.class) ifadesiyle oluşturduğun context nesnesi, Spring IOC Container’ını temsil eder.

//        Vehicle vehicle2 = (Vehicle) context.getBean("vehicle"); // bean adı // bean(name="") ile ismi değiştirilmeden önce metot adı ile çağrılırdı
//        Vehicle vehicle2 = context.getBean(Vehicle.class); // Vehicle.class şeklinde değer gönderilirse eğer Vehicle sınıfını kullanan bir bean varsa o döner fakat daha fazla bean varsa hata verir. Bunun için ilk parametrede adını vermek gerekir.

//        System.out.println("spring : " + vehicle2.getName());
//
//        String hello = context.getBean(String.class);
//        System.out.println("spring : " + hello);
//
//        Integer number = context.getBean(Integer.class);
//        System.out.println("spring : " + number);

        Vehicle audiA3 = context.getBean("AudiVehicle", Vehicle.class);
        Vehicle bmw3 = context.getBean("BMWVehicle", Vehicle.class);
        Vehicle mercedesC = context.getBean("MercedesVehicle", Vehicle.class);

        System.out.printf("%s , %s , %s", audiA3.getName(), bmw3.getName(), mercedesC.getName() + "\n");

        System.out.println(context.getBean(Vehicle.class).getName()); // bu kod Vehicle classını kullanarak birden fazla bean oluşturulmuşsa hata vereceğini söylemiştik fakat eğerki bu beanlerden biri @Primary ile işaretlenmişse o bean döner. @Primary ile işaretlenmiş olan bean, diğer beanlerden daha öncelikli olarak kullanılır. Eğer birden fazla bean varsa ve hiçbiri @Primary ile işaretlenmemişse hata verir.



    }
}
