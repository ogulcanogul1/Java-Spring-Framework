package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.example.beans") // bu paket içinde veya alt paketlerdeki tüm class'ları tarar
@Configuration
public class ProjectConfig {

}


/*
Spring Core & Spring MVC : tüm projeler için temel oluşturur. Bu yüzden Spring Core denir.
-----------------------------------------------------
Spring Boot : Kısa sürede web uygulaması oluşturmak için tanıtıldı. Daha küçük parçalara odaklanır (Microservice)
----------------------------------------------------
Spring Data : Spring Data, veritabanı işlemlerini (CRUD, query vs.) çok daha kolay, hızlı ve az kodla yapmamızı sağlayan bir çatı projedir.doğrudan bir ORM (Object-Relational Mapping) değildir.Ama ORM ile çalışmayı kolaylaştıran bir çatı (abstraction) sağlar.

Hibernate: Evet, bu doğrudan bir ORM framework’üdür. JPA’nın en yaygın implementasyonudur.
JPA :Java’nın ORM için oluşturduğu standart arayüz kümesidir. Kendisi ORM değildir, bir spesifikasyondur.
Spring Data JPA :Hibernate gibi ORM'lerle çalışmayı kolaylaştıran, kod miktarını azaltan bir Spring modülüdür. Kendi ORM değildir, ama ORM'i (JPA) kullanır.
---------------------------------------------------------

Spring Cloud : Spring Cloud, dağıtık sistemler (yani mikroservis mimarileri) için geliştirilmiş bir Spring çatısıdır.Amacı: Mikroservislerin birbirleriyle güvenli, ölçeklenebilir, yapılandırılabilir ve hataya dayanıklı biçimde haberleşmesini kolaylaştırmaktır.

Spring Cloud, mikroservislerin yönetimini, konfigürasyonunu, iletişimini ve güvenliğini sağlamak için kullanılan bir araçlar bütünüdür.

---------------------------------------------------------

Spring Security

---------------------------------------------------------

Spring Session : Spring Session, Spring uygulamalarında oturum yönetimini kolaylaştıran bir modüldür. Oturum bilgilerini (session data) yönetmek için kullanılabilir. Özellikle dağıtık sistemlerde oturum bilgilerini merkezi bir yerde saklamak için kullanılır.

---------------------------------------------------------

Spring Integration : Spring Integration, uygulamalar arasında veri alışverişini kolaylaştıran bir modüldür. Farklı sistemler arasında veri akışını yönetmek için kullanılabilir. Mesaj tabanlı mimarilerde (message-driven architectures) veri alışverişini kolaylaştırır.

---------------------------------------------------------

Spring AMQP : Spring AMQP, RabbitMQ gibi mesajlaşma sistemleri ile entegrasyonu kolaylaştıran bir modüldür. Mesaj tabanlı uygulamalar geliştirmek için kullanılabilir.


*/
