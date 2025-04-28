package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/courses").setViewName("courses.html");
        // /courses => courses.html 'e yönlendiriyoruz static işlemler bu şekilde tanımlanabilir.
        registry.addViewController("/about").setViewName("about.html");
    }
}

/*
    Singleton ve Prototype bütün java uygulamalarınmda bulunan scope .çeşitleridir.
    Sadece Spring Web projelerinde bulunan => Request, Session, Application


1. Request Scope @RequestScope
Kullanıcı bir HTTP isteği gönderir: (/login, /save)

Bu istek için bir tane özel obje yaratılır.

İstek bitince obje yok edilir.

----------------------------------------------------------------------------------------------------
2. Session Scope @SessionScope
Kullanıcı web sitesinde oturum (session) açar.

O kullanıcıya ait özel bir nesne oluşturulur ve session boyunca yaşar.

----------------------------------------------------------------------------------------------------
3. Application Scope @ApplicationScope
Tüm uygulama için bir kere nesne yaratılır.

Herkes o nesneyi kullanır.
----------------------------------------------------------------------------------------------------

 Session Scope Mantığı
Session scope şu anlama gelir:
➔ Her kullanıcıya kendi oturumu (session) için kendi özel nesnesi verilir.
➔ Aynı kullanıcı farklı cihazlardan giriş yaparsa, farklı sessionlar oluşur!
➔ Dolayısıyla telefon ve PC iki farklı session olarak görülür ve iki ayrı nesne oluşur.

----------------------------------------------------------------------------------------------------
@Scope("singleton") dediğinde ➔ Spring kendi container’ı içinde tek nesne yaratır ve hep onu kullanır.

@Scope("application") dediğinde ➔ Web uygulamasının ServletContext'i için bir tane bean yaratılır.

Fakat pratikte (özellikle Spring Boot gibi klasik MVC projelerinde) ikisinin davranışı aynıdır.

Yani: web uygulaması Spring Boot ile yazıldıysa, Singleton ve Application Scope neredeyse %99 aynı çalışır.
*/
