package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.example.beans") // bu paket içinde veya alt paketlerdeki tüm class'ları tarar
@Configuration
public class ProjectConfig {

}


/*
Bean vs Component

Eğerki bir sınıftan birden fazla nesne oluşturulacaksa bu bean ile yapılmalıdır. Componentle birden fazla nesne oluşturulamaz. Component ile sadece bir tane nesne oluşturulur. Component, Spring’in IoC container’ı tarafından yönetilen bir nesnedir. Yani, Spring uygulaması başlarken IoC container tarafından oluşturulur ve bir bean olarak kaydedilir.

Araba sınıfımız olsun
=> Audi ve BMW field'larına sahip iki tane nesne olsun bu nesneler IOC container'a kaydedilmek isteniyorsa kesinlikle bean kullanılmalıdır.Component bir sınıfta sadece 1 tane instance'ın oluşturulmasına izin verir.
* */
