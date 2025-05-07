package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);

    @Query("SELECT c FROM Contact c WHERE c.status = :status") // JPQL // Contact c ifadesi ile c'nin Contact nesnesi olduğu belirtiliyor , Select c ifadesi ile bütün contact nesneleri alınıyor
    //@Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status",nativeQuery = true) SQL
    Page<Contact> findByStatus(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2") // parametreleri sırasıyla alır ?1 => 1. parametre, ?2 => 2. parametre
    int updateStatusById(String status, int id);

    Page<Contact> findOpenMsgs(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying // veri tabanında değişiklik olacaksa @Transactional ve @Modifying eklenmeli
    int updateMsgStatus(String status, int id);

    @Query(nativeQuery = true)
    Page<Contact> findOpenMsgsNative(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true)
    int updateMsgStatusNative(String status, int id);

}

/*

JPQL

@Query("SELECT c FROM Contact c WHERE c.status = :status")

Nesne temellidir. Yani Java’daki Entity sınıfı (Contact) ve onun alanları (status) kullanılır.

SQL değil, JPA standardına özel bir dil (JPQL) kullanılır.

Veritabanından değil, Java sınıflarından sorgulama yapılır gibi yazılır.

Veritabanı bağımsızdır. (MySQL, PostgreSQL fark etmez.)

Avantajları:

Okuması daha kolay.

Taşınabilirliği yüksektir.

Hataları derleme zamanında yakalamak daha kolaydır.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Native SQL

@Query("SELECT c FROM Contact c WHERE c.status = :status")

Nesne temellidir. Yani Java’daki Entity sınıfı (Contact) ve onun alanları (status) kullanılır.

SQL değil, JPA standardına özel bir dil (JPQL) kullanılır.

Veritabanından değil, Java sınıflarından sorgulama yapılır gibi yazılır.

Veritabanı bağımsızdır. (MySQL, PostgreSQL fark etmez.)

Avantajları:

Okuması daha kolay.

Taşınabilirliği yüksektir.

Hataları derleme zamanında yakalamak daha kolaydır.
*/

/*
@Modifying
Bu anotasyon, JPQL veya native SQL ile veri üzerinde değişiklik (insert, update, delete gibi) yapıldığını belirtir.

Ne zaman gerekir?
Sadece veri okumayan, veri değiştiren (değişiklik yapan) sorgularda.

Örnek kullanım yerleri:

UPDATE sorguları

DELETE sorguları

(Opsiyonel olarak) INSERT INTO ... gibi native sorgular

Eğer @Modifying koymazsan, Spring Data JPA sorguyu salt okunur (read-only) olarak algılar ve hata alırsın.

----------------------------------------------------------------------------------------------------------------------------

@Transactional
Bu anotasyon, belirli bir işlemin bir veritabanı işlemi (transaction) olarak yürütülmesini sağlar. Yani ya tamamen başarılı olur ya da tamamen geri alınır (rollback yapılır).

Ne zaman gerekir?

Veritabanında değişiklik yapan işlemler (insert, update, delete)

Birden fazla işlem birlikte yapılacaksa ve tutarlılık gerekiyorsa

Spring Data JPA bazı durumlarda @Transactional anotasyonunu otomatik uygular (özellikle yazma işlemleri için), ancak kesin olması için açıkça yazmak en doğrusudur.
*/

/*
 pageable parametresine verdiğim değeri JPQL sorgumla harmanlayarak doğru bir değer verecek yani ama bu Native yani direk sql diliyle yazılmış olsaydı pageable işe yaramazdı
*/

/*

Ya @Query ile repsotiory katmanında sorgular yazılır.
ya da ilgili Entity sınıfında (Contact) @NamedQuery , @NamedNativeQuery ile sorgular yazılır.

@Query kullanıldığında , SqlResultSetMapping kullanmaya gerek yoktur. Çünkü JPQL ile yazıldığı için, JPA otomatik olarak Contact nesnesine dönüştürür. Ayrıca @NamedQuery'de de SqlResultSetMapping kullanmaya gerek yoktur. Çünkü JPA otomatik olarak Contact nesnesine dönüştürür. Sadece SQL ile kodu entity sınıfımızın üstünde yazarken @NamedNativeQuery ile birlikte SqlResultSetMapping kullanmalıyız. Çünkü SQL ile yazıldığı için, JPA otomatik olarak Contact nesnesine dönüştürmez. Bu durumda SqlResultSetMapping ile dönüşüm yapmalıyız.

@Query kullanıldığında içinde JPQL kodu da SQL kodu olsa her türlü pageable çalışır. @NamedQuery'de de çalışır sadece NamedNativeQuery'de çalışmaz.
*/
