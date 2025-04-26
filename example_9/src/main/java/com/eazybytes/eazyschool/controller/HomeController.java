package com.eazybytes.eazyschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value={"","/","home"}) // / ,"" ekleyerek default anasayfa yönlendirmesi yapıyoruz.
    public String displayHome(Model model)
    {
        model.addAttribute("username" , "Ali"); // model ile veri gönderiyoruz. Bir viewmodel tanımlanıp bu modelin içine de verilebilir.
        return "home.html";
    }
}

//Spring Boot DevTools ne yapar?
//Java dosyalarında (.java) değişiklik olursa otomatik backend restart yapar.
//
//Ayrıca statik dosyalarda (templates içindeki .html, static içindeki .css, .js) değişiklik varsa,
//sayfayı otomatik yenileyebilir (live reload).



//1. Frontend (HTML, CSS, JS) için:
//Live Reload vardır.
//
//        Yani dosyayı (.html, .css, .js) kaydedince → tarayıcı otomatik yenilenir.
//
//Sunucu (Spring Boot) restart olmaz.
//
//Sadece tarayıcı yeniler, güncel dosyayı gösterir.
//
//2. Backend (Java kodları) için:
//Live Reload diye bir şey yoktur.
//
//Java dosyasında (.java) bir değişiklik yaparsan:
//
//DevTools, otomatik olarak uygulamayı restart eder (yani küçük bir sunucu yeniden başlatması yapar).
//
//Spring Boot uygulaması arka planda kapanır ve yeniden açılır.
//
//Bu işlem hızlıdır ama yine de bir restart demektir.

// spring.thymeleaf.cache=false + devtools kullanılmalı en iyi deneyim için.
