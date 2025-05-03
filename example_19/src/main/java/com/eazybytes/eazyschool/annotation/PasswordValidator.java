package com.eazybytes.eazyschool.annotation;

import com.eazybytes.eazyschool.validations.PasswordStrengthValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented //  @PasswordValidator kullanıldığında, bu anotasyon JavaDoc’a dahil edilir.
@Constraint(validatedBy = PasswordStrengthValidator.class) // validatedBy: Hangi sınıfın bu anotasyonu kontrol edeceğini (yani doğrulama yapacağını) belirtir.
@Target( { ElementType.METHOD, ElementType.FIELD }) // Bu anotasyonun nerelere uygulanabileceğini belirtir:
//ElementType.FIELD => Alanlara uygulanabilir (örneğin bir String password; alanına)
//ElementType.METHOD => Getter metoduna da uygulanabilir
@Retention(RetentionPolicy.RUNTIME) //RetentionPolicy.RUNTIME => Uygulama çalışırken bu anotasyon hala erişilebilir olur.
public @interface PasswordValidator {
    String message() default "Please choose a strong password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
