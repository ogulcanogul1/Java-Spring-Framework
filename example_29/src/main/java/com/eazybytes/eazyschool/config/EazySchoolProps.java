package com.eazybytes.eazyschool.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@Component("eazySchoolProps")
@Data
@ConfigurationProperties(prefix = "eazyschool") // Bu prefix, application.properties içindeki ilgili ayarların hangi isimle başlayacağını belirtir. Böylece Spring, sadece bu prefix ile başlayan ayarları bu sınıfa bağlar.
@Validated
public class EazySchoolProps {

    @Min(value=5, message="must be between 5 and 25")
    @Max(value=25, message="must be between 5 and 25")
    private int pageSize;
    private Map<String, String> contact;
    private List<String> branches;

}
