package com.eazybytes.eazyschool.validations;

import com.eazybytes.eazyschool.annotation.FieldsValueMatch;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldsValueMatchValidator
        implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();

    }

    @Override
    public boolean isValid(Object value,ConstraintValidatorContext context) { // Object value parametresi bu annatasyonun uygulandığı sınıfın (örneğin Person) nesnesidir. Yani Person nesnesi gelecektir. Çünkü bu annatasyon class üstünde tanımlanır ve bu sebeple sınıfın nesnesi gelir.
        Object fieldValue = new BeanWrapperImpl(value) // fieldlara erişmek için BeanWrapperImpl kullanılır.
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);
        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
