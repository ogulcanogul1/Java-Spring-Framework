package com.example.beans;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 3. way
@Component
public class Person {
    private String name;
    private int age;

    @Autowired(required = false) // otomatik bağlama
    private Vehicle vehicle;//Eğer vehicle sınıfı bir bean bulamazsa  @AutoWired(required = false) yazılabilir. hata alınmaz.Normalde Bean Bulunamadı hatası verir. (Vehicle @Component olmazsa veya bean olmazsa) null değeri döner.;

    @Autowired // constructor injection @AotuWired sadece okunabilirliği arttırsın diye konulur
    public Person(Vehicle vehicle) {
        System.out.println("Person created");
    }

    @PostConstruct
    public void init() { // constructor ile de yapılabilir
        name = "Ali";
        age = 30;
    }

    public Person() {
        System.out.println("Person created");
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

//    @Autowired // belirli bir koşula göre vs bağımlılık eklenebilir. Bu yüzden burada @Autowired kullanılarak setter injection yapılabilir.Setter injection field injectona göre daha fazla esneklik sağlar.
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String hello() {
        return "Hello from Person";
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", vehicle=" + vehicle +
                '}';
    }
}



// @AutoWired kullanılacak ise sınıflar (ikiside) @Component ile işaretlenerek kontrol spring'e bırakılır. Bu component başka sınıfta kullanıldığında kullanıldığı field'ın üstüne @AutoWired eklenir.


//public class Person {
//    private String name;
//    private int age;
//    private Vehicle vehicle;
//    public Person() {
//        System.out.println("Person created");
//    }
//    public Vehicle getVehicle() {
//        return vehicle;
//    }
//    public void setVehicle(Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String hello() {
//        return "Hello from Person";
//    }
//
//    @Override
//    public String toString() {
//        return "Person{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                ", vehicle=" + vehicle +
//                '}';
//    }
//}



