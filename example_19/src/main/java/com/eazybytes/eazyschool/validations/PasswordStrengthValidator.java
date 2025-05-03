package com.eazybytes.eazyschool.validations;

import com.eazybytes.eazyschool.annotation.PasswordValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements
        ConstraintValidator<PasswordValidator, String> { // Validator + hangi türe uygulanacağı

    List<String> weakPasswords;

    @Override // opsiyonel metot
    public void initialize(PasswordValidator passwordValidator) {
        weakPasswords = Arrays.asList("12345", "password", "qwerty");
    }

    @Override // zorunlu metot , Validator’ın esas mantığını içerir. Anotasyon uygulanmış alanın değerinin geçerli (valid) olup olmadığını belirleyen tek yerdir. Her doğrulama gerektiğinde çağrılır (örneğin form submit edildiğinde, ya da nesne validasyonu yapıldığında).
    public boolean isValid(String passwordField,
                           ConstraintValidatorContext cxt) {
        return passwordField != null && (!weakPasswords.contains(passwordField));
    }


}

/*
ConstraintValidatorContext içinde kullanılan metotlar (senin bahsettiğin gibi cxt ya da ctx olarak kullanılan değişkenin metotları), validasyon sonucunda kullanıcıya özelleştirilmiş hata mesajları göstermek için kullanılır. Aşağıda yaygın kullanılanları ve ne işe yaradıklarını açıklıyorum:

disableDefaultConstraintViolation()
Bu metot, anotasyon içinde tanımladığın message değerini devre dışı bırakır.

Yani: "message" default "Please choose a strong password" mesajı gösterilmez.

Ne zaman kullanılır?
Kendi özel mesajını buildConstraintViolationWithTemplate(...) ile eklemek istediğinde.

--------------------------------------------------------------------------------
buildConstraintViolationWithTemplate(String message)
Bu metot, kendine ait özel bir hata mesajı oluşturmanı sağlar.


context.buildConstraintViolationWithTemplate("Şifre çok zayıf!")
Bu şekilde, daha açıklayıcı ve kullanıcıya özel hata mesajları verebilirsin.


--------------------------------------------------------------------------------

addConstraintViolation()
Bu metot, buildConstraintViolationWithTemplate(...) ile oluşturduğun mesajı aktif hale getirir.

Aksi takdirde mesaj görünmez. Bu metodu mutlaka çağırmalısın.

addPropertyNode(String propertyName)
(İsteğe bağlıdır – genellikle sınıf seviyesinde kullanılır.)

Eğer anotasyon bir sınıfın tamamına uygulanıyorsa, bu metotla hangi field’a hata mesajının ait olduğunu belirtirsin.

*/
