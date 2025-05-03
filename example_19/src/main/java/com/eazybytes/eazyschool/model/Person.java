package com.eazybytes.eazyschool.model;

import com.eazybytes.eazyschool.annotation.FieldsValueMatch;
import com.eazybytes.eazyschool.annotation.PasswordValidator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@Entity
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Passwords do not match!"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email addresses do not match!"
        )
})
public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int personId;

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;

    @NotBlank(message="Confirm Email must not be blank")
    @Email(message = "Please provide a valid confirm email address" )
    @Transient
    private String confirmEmail;

    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    @PasswordValidator
    private String pwd;

    @NotBlank(message="Confirm Password must not be blank")
    @Size(min=5, message="Confirm Password must be at least 5 characters long")
    @Transient // @Transient anotasyonu, bir field (alan) veya property için uygulanabilir ve bu alanda yer alan verinin veritabanı tablosunda saklanmaması gerektiğini belirtir.
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId",nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId",nullable = true) // Hem name hem referencedColumnName veritabanı sütun isimleridir, Java’daki field isimlerinden bağımsızdır.
    private Address address;
}

/*
 1. FetchType nedir? (Ne zaman veri çekilecek?)
FetchType, ilişkili entity’nin ne zaman veritabanından yükleneceğini belirtir.

Türleri:

EAGER (hemen)	Ana entity yüklenirken ilişkili olan da hemen yüklenir (join veya ayrı query ile)
LAZY (geç)	Ana entity yüklenirken ilişkili olan şimdilik yüklenmez, sadece erişildiğinde yüklenir

--------------------------------------------------------------------------------------------------------

2. CascadeType nedir? (İlişkili entity’lere işlem zinciri)
CascadeType, bir entity'ye yapılan işlemin, ilişkili entity’e de otomatik uygulanıp uygulanmayacağını belirler.

Türleri:
ALL : Hepsi (persist, merge, remove, refresh, detach)
PERSIST : save işlemi ilişkili entity’e de uygulanır
MERGE :	merge() işlemi ilişkili entity’ye de uygulanır
REMOVE : delete yapılınca ilişkili entity de silinir
REFRESH : Ana entity yenilenirken ilişkili de yenilenir
DETACH : Entity detach edilince ilişkili entity de detach edilir

 Not: Her zaman CascadeType.ALL kullanmak önerilmez. Özellikle REMOVE dikkatli kullanılmalı çünkü ilişkili tabloyu yanlışlıkla silebilirsin.
*/
/*

1. CascadeType.PERSIST - Save işlemi
Ne zaman kullanılır?
Yeni bir entity persist edilirken, ilişkili entity'ler de otomatik olarak kaydedilmelidir.

Ana entity oluşturulurken, ilişkili verilerin de veritabanına eklenmesi gerekmektedir.

Örnek:
Bir Order ve ona bağlı OrderItem entity'si düşünelim. Bir Order oluşturulduğunda, ilişkili OrderItem'ların da otomatik olarak kaydedilmesi isteniyor.

-------------------------------------------------------------------------------------------------------

2. CascadeType.MERGE - Merge işlemi

Ne zaman kullanılır?
Var olan bir entity merge edilirken, ilişkili entity'ler de otomatik olarak güncellenmelidir.

Ana entity üzerinde yapılan değişikliklerin ilişkili entity'lere de yansıması sağlanır.

Bir User entity'si ve ilişkili bir Address entity'si olduğunu düşünelim. Eğer kullanıcıyı güncellediğinde, ona ait adres bilgileri de güncellenmelidir.

-------------------------------------------------------------------------------------------------------

3. CascadeType.REMOVE - Silme işlemi
Ne zaman kullanılır?
Bir entity silindiğinde, ilişkili entity'lerin de otomatik olarak silinmesi gerekir.

İlişkili veri silindiğinde, ana entity'nin de silinmesi sağlanır.

Bir Post entity'si ve ona bağlı bir Comment entity'si olduğunu düşünelim. Bir Post silindiğinde, o post’a ait tüm yorumların da silinmesi gerekir.

-------------------------------------------------------------------------------------------------------
CascadeType.REFRESH - Yenileme işlemi

Ne zaman kullanılır?
Bir entity refresh edildiğinde, ilişkili entity'lerin de yenilenmesi gerekir.

Ana entity üzerinde yapılan değişikliklerin, ilişkili entity'ler ile senkronize edilmesi sağlanır.

Örnek:
Bir Product entity'si ve ona bağlı bir Inventory entity'si olduğunu düşünelim. Bir ürün güncellendiğinde, o ürüne ait envanter bilgileri de yenilenmelidir.

JPA'de yenilemek kelimesi genellikle EntityManager.refresh(entity) metodunu ifade eder.

Bir entity’ye CascadeType.REFRESH eklersen, ana entity yenilenirken ilişkili entity’ler de yenilenir.

@OneToOne(cascade = CascadeType.REFRESH)
private Address address;
Bu durumda entityManager.refresh(user) dersen, user'ın address’i de veritabanından yeniden yüklenir.



-------------------------------------------------------------------------------------------------------

5. CascadeType.DETACH - Detach işlemi

Ne zaman kullanılır?
Bir entity detach edildiğinde, ilişkili entity'lerin de detach edilmesi gerekir.

EntityManager'dan çıkarılan bir entity ile ilişkili veriler de bağımsız hale gelir.

Örnek:
Bir Employee entity'si ve ona bağlı Department entity'si olduğunu düşünelim. Bir Employee detach edildiğinde, ilişkili Department entity’sinin de detach edilmesi gerekir.

 Ne Zaman Olur?

entityManager.detach(entity);
Bu komutu verdiğinde:

entity nesnesi artık Persistence Context tarafından izlenmez.

Üzerinde değişiklik yapsan bile veritabanına yansımaz.

-------------------------------------------------------------------------------------------------------

@Entity // 1. Person owns Address ilişkisi (FK Person tablosunda)
public class Person {

    @OneToOne
    @JoinColumn(name = "address_id") // FK Person tablosunda
    private Address address;
}

-------------------------------------------------------------------------------------------------------

@Entity  // 2. Address owns Person ilişkisi (FK Address tablosunda)
public class Address {

    @OneToOne
    @JoinColumn(name = "person_id") // FK Address tablosunda
    private Person person;
}

*/