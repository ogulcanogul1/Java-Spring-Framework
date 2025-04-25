package com.example.beans;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(BeanDefinition.SCOPE_PROTOTYPE) // Prototype her bir nesne talebinde yeni bir nesne oluşturur. Singleton tek bir nesneyi bütün çağrılarda döndürür. Eğer ki bir bean'in içeriği değiştirilecek ise bunu prototype yapmalıyız. Eğer ki bir bean'in içeriği değişmeyecek ise bunu singleton yapmalıyız. Prototype de eager loading , lazy laoding gibi davranışlar söz konusu değildir. Nesne her talep edildiğinde yeniden üretilir. Singleton => immutuable objects , Prototype => mutable objects için daha uygundur.
@Component
public class VehicleService {
    private String TyreBrand;
    private String EngineType;
    private String SpeakerBrand;

    public String getEngineType() {
        return EngineType;
    }

    public void setEngineType(String engineType) {
        EngineType = engineType;
    }

    public String getSpeakerBrand() {
        return SpeakerBrand;
    }

    public void setSpeakerBrand(String speakerBrand) {
        SpeakerBrand = speakerBrand;
    }

    public String getTyreBrand() {
        return TyreBrand;
    }

    public void setTyreBrand(String tyreBrand) {
        TyreBrand = tyreBrand;
    }
}
