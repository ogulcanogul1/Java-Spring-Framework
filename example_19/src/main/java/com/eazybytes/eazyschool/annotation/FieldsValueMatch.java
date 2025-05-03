package com.eazybytes.eazyschool.annotation;

import com.eazybytes.eazyschool.validations.FieldsValueMatchValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "Fields values don't match!";

    String field(); // karşılaştırma elemanı 1

    String fieldMatch(); // karşılaştırma elemanı 2

    @Target({ ElementType.TYPE }) // ElementType.TYPE ifadesi, sınıflara (class), arayüzlere (interface), enumlara (enum) ve anotasyonlara (annotation) uygulanan anotasyonlar için kullanılır. Y
    @Retention(RetentionPolicy.RUNTIME)
    @interface List { // hem password kontrolü hem de email kontrolü için bu tanımlama yapılmalıdır Person classında bakılabilir.
        FieldsValueMatch[] value();
    }
}