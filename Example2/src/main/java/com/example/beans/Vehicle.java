package com.example.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Vehicle {
    private String name;

    public String getName() {
        return name;
    }

    @PostConstruct // Componentde değer ataması beande yapıldığı gibi yapılamaz burada değer atayabiliyoruz.
    public void initialize() {
        this.name = "Honda";
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Vehicle bean is being destroyed");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String hello() {
        return "Hello from Vehicle";
    }
}


/*
@Component : bir sınıfı Spring Bean olarak tanımlar. Yani Spring’e "Bu sınıfın bir nesnesini oluştur ve yönet" demek olur. Bu sınıf, Spring uygulaması başlarken IoC container tarafından oluşturulur ve bir bean olarak kaydedilir.

@Service : genellikle iş mantığını içeren sınıflar için kullanılır. @Service anotasyonu, @Component anotasyonunun bir uzantısıdır. Yani, @Service ile işaretlenmiş bir sınıf da aslında bir Spring Bean'dir. Ancak, @Service anotasyonu, bu sınıfın iş mantığı katmanında olduğunu belirtmek için daha anlamlıdır.

@Repository : veri erişim katmanında kullanılan sınıflar için kullanılır. @Repository anotasyonu, veri erişim katmanındaki sınıfların Spring tarafından yönetilmesini sağlar. Ayrıca, veri erişim katmanında hata yönetimi ve veri erişim işlemlerinin yönetimi gibi ek özellikler sağlar.

@Controller : genellikle web uygulamalarında kullanılan sınıflar için kullanılır. @Controller anotasyonu, bu sınıfın bir denetleyici olduğunu belirtir. Denetleyiciler, HTTP isteklerini alır ve yanıtlar. Ayrıca, iş mantığı katmanına yönlendirme yapabilirler.

@Service, @Controller, @Repository ve @Component annotasyonlarının hepsi aslında @Component annotasyonundan türetilmiştir.
* */
