package com.example.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy // Eğer bir çok bean varsa ve bazı beanler her zaman kullanılmıyorsa , örneğin bir web uygulamasında hesabımız olduğunu düşünelim bu hesabı silmek istediğimizde kullanılan beanleri her zaman kullanmayız nadiren kullanılır ve bu sebeple @Lazy kullanarak geç yüklenmesini sağlayarak performans kazanımı sağlayabiliriz.
public class Person {
    private String name = "Ali";
    private int age = 30;

    public Person() {
        System.out.println("Person created");
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

/*
 Eager Loading (Hemen Yükleme) => "Uygulama açılırken her şeyi yükle."
 Lazy Loading (Tembel Yükleme) => "Gerekmedikçe yükleme." , Nesne ya da veri ilk kez ihtiyaç duyulduğunda (kullanıldığında) yüklenir.
*/




