package com.eazybytes.eazyschool.rest;

import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.model.Response;
import com.eazybytes.eazyschool.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/api/contact",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}) // produces XML formatında döneceğini belirtiyoruz. İlgili kütüphaneler eklenmeli.
@CrossOrigin(origins="*") // "*" CORS Politikası gelen bütün isteklere izin veriyor origins = "http://localhost:3000" değeride verilebilir.
public class ContactRestController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/getMessagesByStatus")
    //@ResponseBody
    public List<Contact> getMessagesByStatus(@RequestParam(name = "status")  String status){
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMsgsByStatus")
    //@ResponseBody
    public List<Contact> getAllMsgsByStatus(@RequestBody Contact contact){
        if(null != contact && null != contact.getStatus()){
            return contactRepository.findByStatus(contact.getStatus());
        }else{
            return List.of();
        }
    }

    @PostMapping("/saveMsg")
    // @ResponseBody
    public ResponseEntity<Response> saveMsg(@RequestHeader("invocationFrom") String invocationFrom,
                                            @Valid @RequestBody Contact contact){
        log.info(String.format("Header invocationFrom = %s", invocationFrom));
        contactRepository.save(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMsgSaved", "true")
                .body(response);
    // ResponseEntity<Response> ile body'nin Response tipinde olduğunu belirtiyoruz
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity){
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key, value) -> {
            log.info(String.format(
                    "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
        });
        Contact contact = requestEntity.getBody();
        contactRepository.deleteById(contact.getContactId());
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message successfully deleted");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq){
        Response response = new Response();
        Optional<Contact> contact = contactRepository.findById(contactReq.getContactId());
        if(contact.isPresent()){
            contact.get().setStatus(EazySchoolConstants.CLOSE);
            contactRepository.save(contact.get());
        }else{
            response.setStatusCode("400");
            response.setStatusMsg("Invalid Contact ID received");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        response.setStatusCode("200");
        response.setStatusMsg("Message successfully closed");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}

/*
RequestEntity<T> sınıfı, Spring Framework'te gelen bir sınıftır ve bir HTTP isteğinin hem gövdesini (body) hem de meta verilerini (headers, method, URL vb.) içeren zengin bir temsilidir.

Kısaca: @RequestBody sadece veriyi alır,
ama RequestEntity body + header + HTTP method + URL gibi tüm bilgileri içerir.

public ResponseEntity<?> saveMsg(RequestEntity<Contact> request) {
    Contact contact = request.getBody(); // body'yi al
    HttpHeaders headers = request.getHeaders(); // header'ları al
    HttpMethod method = request.getMethod(); // POST mu GET mi?
    URI uri = request.getUrl(); // hangi URL'den geldi?

    // örnek işlem
    log.info("Header invocationFrom = " + headers.getFirst("invocationFrom"));

    ...
}

---------------------------------------------------------------------------------------------------------------------------------------------------------
public class Contact {
    private Long contactId;
    // diğer alanlar
    // getter-setter
}


{
  "contactId": 123 // postmandan yapılan istek
}

public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity) // metot


Neler Oluyor? Nasıl Çalışıyor?

1. RequestEntity<Contact> kullanıyorsun
Spring, gelen HTTP isteğini alır ve gövdesindeki JSON veriyi Contact nesnesine otomatik olarak bağlar (deserialization).

2. JSON sadece contactId içeriyor
Ama bu yeterli! Çünkü Contact sınıfında contactId alanı tanımlı ve bu alanın setter'ı var.

3. Spring şu işlemi yapıyor:
Gövdedeki contactId’yi bulur,

Yeni bir Contact nesnesi oluşturur,

setContactId(123) ile değeri yerleştirir,

Ve bu nesneyi requestEntity.getBody() ile sana verir.
---------------------------------------------------------------------------------------------------------------------------------------------------------

1. @RequestBody
@RequestBody, HTTP isteğinden gelen body'yi, bir Java nesnesine dönüştürmek için kullanılır. Bu anotasyon, JSON, XML gibi formatlarda gönderilen verileri, Java nesnelerine otomatik olarak bağlamak için kullanılır.


2. RequestEntity
RequestEntity, HTTP isteği ile ilgili daha gelişmiş bir yapı sağlar. Bu yapı yalnızca body verisini değil, aynı zamanda başlıklar (headers), HTTP metodunu, ve URL'yi de içerir. RequestEntity, HTTP isteği hakkında daha fazla bilgi almak gerektiğinde kullanılır.

3. @RequestHeader
@RequestHeader, HTTP isteğinden gelen başlıkları (headers) almak için kullanılır. HTTP başlıkları, istemci ve sunucu arasında taşınan meta verileridir. Örneğin, kimlik doğrulama bilgileri, içerik tipi gibi veriler header kısmında taşınır.

---------------------------------------------------------------------------------------------------------------------------------------------------------

CORS Politikası

CORS (Cross-Origin Resource Sharing), bir web tarayıcısının bir kaynak (API, veri, görsel vb.) başka bir origin'den (kaynak alan adı/protokol/port) talep ettiğinde bu isteğe izin verilip verilmeyeceğini belirleyen bir güvenlik mekanizmasıdır.

CORS, tarayıcının farklı "origin"ler (kaynaklar) arasındaki isteklere nasıl davranacağını belirler.

Bir "origin" şunlardan oluşur:
-Protokol (http / https)
-Alan adı (domain)
-Port (80, 443, vb.)

Örneğin:
Frontend: http://localhost:3000
Backend API: http://localhost:8080
Bu iki adres farklı origin'lerdir. Tarayıcı, bu durumda CORS politikası gereği doğrudan isteğe izin vermez (güvenlik nedeniyle).

Neden Vardır? (Amacı Nedir?)
CORS, tarayıcı tabanlı uygulamalarda şu amaçlarla kullanılır:
- Kötü niyetli sitelerin sizin adınıza başka sitelere istek atmasını engellemek.
- Kullanıcı bilgilerini korumak (örneğin: çerezle gelen oturum bilgileri).
- Sunucu sahibine kontrol hakkı vermek – Hangi domainlerden gelen isteklere izin verileceğini sunucu belirler.

-------------------------------------------------------------------------------------------------
Genel CORS Yapılandırması

public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // tüm endpoint'ler için
                .allowedOrigins("http://localhost:3000") // React uygulaman
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true); // eğer cookie vs. gerekiyorsa
    }
}

Yoksa Her bir Controller'da @CrossOrigin kullanmak zorunda kalırız.
-----------------------------------------------------------------------------------------------------------------------------------------

@JsonProperty: Jackson kütüphanesiyle JSON dönüşümünde, bir Java sınıfındaki alanın JSON çıktısındaki ismini değiştirmek için kullanılır. Örneğin, bir sınıfın alan adı firstName olabilir, ancak JSON çıktısında bu alan first_name olarak görünsün istiyorsanız, @JsonProperty ile bunu belirtebilirsiniz.

@JsonIgnore: Jackson kütüphanesinde, bu anotasyon belirli bir alanın JSON çıktısında yer almasını engeller. Bu, genellikle güvenlik nedenleriyle veya gereksiz alanları dışarıda bırakmak için kullanılır.

*/