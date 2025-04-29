package com.eazybytes.eazyschool.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Contact {
    @NotBlank(message="Name must not be blank")
    @Size(min=3 , message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message="Phone number must not be blank")
    @Pattern(regexp="(^$[0-9]{10})", message="Mobile number must be 10 digits")
    private String phoneNumber;

    @NotBlank(message="Email must not be blank")
    @Email(message="Email should be valid")
    private String email;

    @NotBlank(message="Subject must not be blank")
    @Size(min=5 , message="Subject must be at least 5 characters long")
    private String subject;

    @NotBlank(message="Message must not be blank")
    @Size(min=10 , message="Message must be at least 10 characters long")
    private String message;
}

// jakarta.validation.constraints , - org.hibernate.validator.constraints validation 2 farklı uygulanabilir.

/*
Jakarta Bean Validation Annotation'ları

@NotNull   Alanın null olmamasını zorunlu kılar.

@NotEmpty 	Koleksiyon, dizi veya String'in boş olmamasını sağlar (null olabilir ama "" veya boş array olamaz).

@NotBlank	String boş ve sadece boşluklardan oluşamaz (aynı zamanda null da olamaz).

@Size(min, max)	String, Array veya Collection'ın boyutunu sınırlar.

@Min(value)	Sayısal değerin minimum olması gerekir.

@Max(value)	Sayısal değerin maximum olması gerekir.

@Email	Email formatında olmasını zorunlu kılar.

@Pattern(regexp)	Bir Regex desenine uygun olmasını ister.

@Past	Tarihin geçmişte bir tarih olması gerekir.

@Future	Tarihin gelecekte bir tarih olması gerekir.

@AssertTrue	Boolean alanın true olmasını ister.

@AssertFalse	Boolean alanın false olmasını ister.

@Positive	Sayısal alanın pozitif (>0) olması gerekir.

@PositiveOrZero	Sayısal alanın pozitif veya sıfır olması gerekir.

@Negative	Sayısal alanın negatif (<0) olması gerekir.

@NegativeOrZero	Sayısal alanın negatif veya sıfır olması gerekir.

@Length(min, max)	String uzunluğunu sınırlar (Hibernate özelliğidir, @Size gibidir ama sadece String için).
*/
/*
Hibernate Validator'a Özel (Geliştirilmiş) Annotation'lar

@Range(min, max)	Sayısal değeri bir aralıkta sınırlar.

@URL	Bir alanın geçerli URL formatında olup olmadığını kontrol eder.

@CreditCardNumber	Geçerli bir kredi kartı numarası olup olmadığını kontrol eder (Luhn algoritması kullanır).

@ISBN	Geçerli bir ISBN (kitap numarası) formatında olup olmadığını kontrol eder.

@Currency	Para birimi formatı kontrolü yapar.
*/