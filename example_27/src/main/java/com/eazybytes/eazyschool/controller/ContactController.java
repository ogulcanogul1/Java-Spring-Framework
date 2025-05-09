package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.model.Response;
import com.eazybytes.eazyschool.proxy.ContactProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ContactController {

    @Autowired
    ContactProxy contactProxy;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient webClient;

    @GetMapping("/getMessages")
    public List<Contact> getMessages(@RequestParam("status") String status) {
        return contactProxy.getMessagesByStatus(status);
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(@RequestBody Contact contact){
        String uri = "http://localhost:8080/api/contact/saveMsg";
        HttpHeaders headers = new HttpHeaders();
        headers.add("invocationFrom","RestTemplate");
        HttpEntity<Contact> httpEntity = new HttpEntity<>(contact, headers);
        ResponseEntity<Response> responseEntity = restTemplate.exchange(uri, HttpMethod.POST,
                httpEntity,Response.class);
        return responseEntity;
    }

    @PostMapping("/saveMessage")
    public Mono<Response> saveMessage(@RequestBody Contact contact){
        String uri = "http://localhost:8080/api/contact/saveMsg";
        return webClient.post().uri(uri)
                .header("invocationFrom","WebClient")
                .body(Mono.just(contact),Contact.class)
                .retrieve()
                .bodyToMono(Response.class);
    }
}

/*
body(Mono.just(contact), Contact.class)

Ne işe yarar?

HTTP isteğine gönderilecek gövdeyi (body) tanımlar.

Mono.just(contact) => bir adet Contact nesnesini reaktif olarak sarar.

Contact.class => gönderilen nesnenin türünü belirtir (opsiyoneldir ama genelde yazılır).

Tek nesne : body(Mono.just(obj), ObjClass.class)
Liste göndermek : 	body(Flux.fromIterable(list), ObjClass.class)
Boş body :	body(BodyInserters.empty())
Form verisi :	body(BodyInserters.fromFormData("key", "value"))
JSON manuel :	bodyValue(obj) (daha basit, non-reactive yöntem)

-----------------------------------------------------------------------------------------------------------------------------

retrieve()
Ne işe yarar?
İstek gönderildikten sonra yanıtı almayı başlatır.

Hatalı durumları (4xx, 5xx) otomatik olarak WebClientResponseException fırlatarak işler.

-------------------------------------------------------------------------------------------------------------------------------
bodyToMono(Response.class)

Ne işe yarar?
Gelen cevabı Response.class türünde bir Java nesnesine dönüştürür.

Mono çünkü genellikle tek bir sonuç döner (Mono<Response>).

Eğer sunucudan dizi/list dönüyorsa, bodyToFlux(Response.class) kullanılır.
*/
