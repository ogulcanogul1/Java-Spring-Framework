package com.eazybytes.eazyschool.controllers;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.services.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Slf4j
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


//    private static Logger log = LoggerFactory.getLogger(ContactController.class);
    @RequestMapping("/contact")
    public String  displayContactPage(Model model) {

        model.addAttribute("contact", new Contact());   // validasyon için
        return "contact.html";
    }


    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST) // Yukarıdaki metodun düzenlenmiş hali
    public String postContact(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        // validasyon için @ModelAttribute("contact") Contact contact la birlikte kullanılır , @Valid ile birlikte kullanılır
        if(errors.hasErrors()){
            log.error("Validation errors occurred : ", errors.toString());
            return "contact.html";
        }
        boolean response = contactService.logContact(contact);

        return "redirect:/contact";
    }


}

