package com.eazybytes.eazyschool.model;

import lombok.Data;

@Data
public class Contact {
    private String name;
    private String phoneNumber;
    private String email;
    private String subject;
    private String message;
}


//@RequestParam
//String name, @RequestParam String mobileNum,
//@RequestParam String email , @RequestParam String subject,
//@RequestParam String message