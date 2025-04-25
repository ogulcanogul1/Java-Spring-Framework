package com.example.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan({"com.example.beans", "com.example.aspects", "com.example.services"}) // Proje içindeki tüm bileşenleri tarar ve bulduğu bileşenleri otomatik olarak Spring konteynerine ekler.
@EnableAspectJAutoProxy // AOP'yi etkinleştirir.
public class ProjectConfig {

}







