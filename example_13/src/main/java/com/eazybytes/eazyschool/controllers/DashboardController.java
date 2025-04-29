package com.eazybytes.eazyschool.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @RequestMapping("/dashboard")
    public String displayDashboardPage(Model model , Authentication authentication) {

        String username = authentication.getName(); // authentication objesinden username alıyoruz
        String roles = authentication.getAuthorities().toString(); // authentication objesinden role alıyoruz
        model.addAttribute("username", username); // model'e username'i ekliyoruz
        model.addAttribute("roles", roles); // model'e role'i ekliyoruz
        return "dashboard.html";
    }
}
