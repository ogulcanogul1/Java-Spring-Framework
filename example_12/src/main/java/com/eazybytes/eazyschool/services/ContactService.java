package com.eazybytes.eazyschool.services;

import com.eazybytes.eazyschool.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactService {

//    Logger log = LoggerFactory.getLogger(ContactService.class);
    public boolean logContact(Contact contact)
    {
        boolean isSaved = true;
        log.info(contact.toString());
        return isSaved;
    }
}
