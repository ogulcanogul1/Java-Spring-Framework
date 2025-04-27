package com.eazybytes.eazyschool.model;

import lombok.Data;

@Data
public class Holiday {
    private final String day;
    private final String reason;
    private final Type type;


    public enum  Type {
        FESTIVAL, FEDERAL
    }


}


/*
Lombok annotations
@Getter
Her field (alan) için get methodu üretir.

Yani elle getX() gibi methodlar yazmana gerek kalmaz

-----------------------------------------------------------------------------------------------

@Setter
Her field için set methodu üretir.

Yani elle setX() methodları yazmana gerek kalmaz.

-----------------------------------------------------------------------------------------------

@NoArgsConstructor
Parametresiz (boş) bir constructor üretir.

-----------------------------------------------------------------------------------------------
@AllArgsConstructor
Tüm fieldlar için bir constructor üretir.

Bütün değişkenleri parametre olarak alan bir constructor olur.

-----------------------------------------------------------------------------------------------

@RequiredArgsConstructor
Sadece final olan veya @NonNull anotasyonu ile işaretlenen fieldlar için constructor üretir.

-----------------------------------------------------------------------------------------------

@ToString
Tüm fieldlar için toString() methodu oluşturur.

Yani nesneyi yazdırınca düzgün şekilde verilerini gösterir.

------------------------------------------------------------------------------------------------
@EqualsAndHashCode
equals() ve hashCode() methodlarını üretir.

Nesneleri karşılaştırmak için (== yerine doğru karşılaştırma için) kullanılır.

Örneğin bir Set içine aynı nesneden iki tane eklenmemesini sağlar.

------------------------------------------------------------------------------------------------
@Data
"Hepsini birleştirir."

Şunları otomatik olarak ekler:
@Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode

Bütün değişkenler değil, sadece zorunlu olanlar için constructor olur.

--------------------------------------------------------------------------------
Yani @Data yazarsan:

Getter ve Setter'lar otomatik gelir.

toString(), equals() ve hashCode() otomatik gelir.

RequiredArgsConstructor (final ve @NonNull için) otomatik gelir.

--------------------------------------------------------------------------------

@Slf4j // Lombok ile loglama işlemi yapılıyor. @Slf4j ile loglama işlemi yapılabilir.
*/

