package com.eazybytes.eazyschool.controllers;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


//    private static Logger log = LoggerFactory.getLogger(ContactController.class);
    @RequestMapping("/contact")
    public String  displayContactPage() {
        return "contact.html";
    }

//    @RequestMapping(value = "/saveMsg" , method = RequestMethod.POST) // th:action="@{/saveMsg}" ile geliyor.
////    @PostMapping("/saveMsg") yukarıdaki ile aynı işlevi görür
//    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,
//                              @RequestParam String email , @RequestParam String subject,
//                              @RequestParam String message) {
//        log.info("Name: " + name);
//        log.info("Mobile Number: " + mobileNum);
//        log.info("Email: " + email);
//        log.info("Subject: " + subject);
//        log.info("Message: " + message);
//        return new ModelAndView("redirect:/contact");
//    }


    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST) // Yukarıdaki metodun düzenlenmiş hali
    public ModelAndView postContact(Contact contact) {

        boolean response = contactService.logContact(contact);

        return new ModelAndView("redirect:/contact");
    }


}

