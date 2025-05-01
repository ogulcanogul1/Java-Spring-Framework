package com.eazybytes.eazyschool.model;

import lombok.Data;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}

/*
@Entity
@Table
@Column
---------------------------------------------------------------------------------------------------------------------------------------
@GeneratedValue(strategy = GenerationType.AUTO , generator = "native") =>

@GeneratedValue: Bu, birincil anahtar (ID) değerinin nasıl üretileceğini belirler.

strategy = GenerationType.AUTO: JPA, kullanılan veritabanına göre en uygun stratejiyi otomatik olarak seçer (örneğin PostgreSQL’de sequence, MySQL’de identity olabilir).

generator = "native": Bu, özel olarak tanımlanmış bir ID üretim stratejisi adıdır. Aşağıdaki @GenericGenerator ile eşleşir.


@GenericGenerator(name = "native" , strategy = "native") =>
@GenericGenerator: Hibernate’e özgü bir annotasyondur (JPA standardı değil).

name = "native": Yukarıdaki @GeneratedValue ile eşleşmesi için verilir.

strategy = "native": Hibernate’in veritabanına özgü varsayılan stratejiyi kullanmasını sağlar:

---------------------------------------------------------------------------------------------------------------------------------------

@Id
@GeneratedValue
private Long id; sadece bu şekilde tanım id'yi otomatik üretmeye yeter fakat sadece hibernate özel stratejiler için yukarıdaki gibi tanımlar gerekebilir. (@GenericGenerator)


---------------------------------------------------------------------------------------------------------------------------------------
@MappedSuperclass => @MappedSuperclass, bir sınıfın veritabanında tablo olarak karşılığı olmadan, diğer @Entity sınıflarına ortak alanları ve davranışları miras bırakmasını sağlar.


---------------------------------------------------------------------------------------------------------------------------------------
@Enumerated(EnumType.STRING) => @Enumerated Nedir?
JPA'de bir entity içinde enum tipi bir alan varsa, bu alanın veritabanında nasıl saklanacağını belirtmek için @Enumerated anotasyonu kullanılır.

---------------------------------------------------------------------------------------------------------------------------------------
@SpringBootApplication
@EnableJpaRepositories("com.eazyytes.eazyschool.repository")
@EntityScan("com.eazybytes.eazyschool.model)

@EnableJpaRepositories("com.eazybytes.eazyschool.repository")
@EntityScan("com.eazybytes.eazyschool.model")

yukarıdakilerin main metodunun üstünde olması gerekiyor.

*/