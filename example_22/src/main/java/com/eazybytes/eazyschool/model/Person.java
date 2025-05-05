package com.eazybytes.eazyschool.model;

import com.eazybytes.eazyschool.annotation.FieldsValueMatch;
import com.eazybytes.eazyschool.annotation.PasswordValidator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Getter
@Setter
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
    @Transient
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId",nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId",nullable = true)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private EazyClass eazyClass;
    /*Her Person bir eazyClass'a aittir. Person Dependent Entity'dir*/ /*ManyToOne ile JoinColumn kullanılır.*/
    /*
     1. optional = true (Java seviyesinde)
        Bu, Java (Hibernate) tarafında bu ilişkinin nullable olduğunu belirtir.

        Yani eazyClass alanı null olabilir.

        Eğer optional = false olsaydı, Hibernate validasyon sırasında hata fırlatırdı: javax.persistence.PersistenceException.

      2. nullable = true (Veritabanı seviyesinde)
        Bu ise veritabanında ilgili kolonun (class_id) NULL olabileceğini belirtir.

        Eğer nullable = false olursa, veritabanı bu alanın boş olmasına izin vermez ve insert sırasında hata alırsın.
    */

}

/*
 OneToOne bir ilişkim olsaydı ve JoinColumn'u eklediğim bir sınıf olsaydı bu sınıf dependent entity olacak ve foregin keyler burada tanımlanacaktı.
*/

/*
sınıf ve öğrenci classlarım olsa öğrenci classı tanımladığımda kesinlikle bir sınıfa bağlı olmasını istiyorum.

@ManyToOne(fetch = FetchType.LAZY, optional = false) // ZORUNLU hale getirir
@JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = false) // DB tarafında da zorunlu
private EazyClass eazyClass;

optional = false	Java tarafında null olmasına izin verilmez. Entity persist edilirken hata alırsın.
nullable = false	DB tarafında class_id kolonunun NOT NULL olmasını sağlar.
*/
