package com.eazybytes.eazyschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class")
public class EazyClass extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int classId;

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @OneToMany(mappedBy = "eazyClass", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,targetEntity = Person.class)
    private Set<Person> persons;
    /*Bir EazyClass birden çok person içerebilir. mappedBy'a dependent entityde tanımlanan field'ın adı verilir*/
    /*buradaki PERSIST davranışı eğer database'e bir EazySchool nesnesi eklenirse person'u da ekler, Remove vs eklenmemiştir çünkü bir sınıf silinirse sınıfa bağlı öğrencilerin silinmesini istemiyoruz*/

    /*JAVA - private int classId;  => DB class_id*/
}
