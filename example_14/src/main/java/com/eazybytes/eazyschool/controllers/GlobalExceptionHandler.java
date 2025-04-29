package com.eazybytes.eazyschool.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception exception) {
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        errorPage.addObject("errorMessage", exception.getMessage());
        return errorPage;
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView nullPointerExceptionHandler(NullPointerException exception) {
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        errorPage.addObject("errorMessage", "Null Pointer Exception occurred: " + exception.getMessage());
        return errorPage;
    }
}
//böyle bir kodda bir nullPointerException hatası alırsam hangi metot tetiklenecek


//Spring @ExceptionHandler anotasyonlarını değerlendirirken, en spesifik (özgül) olanı tercih eder. Yani:

//Eğer bir istisna sınıfı başka bir istisna sınıfının alt sınıfıysa (örneğin NullPointerException, Exception'ın bir alt sınıfıdır),
//
// Ve her ikisi için @ExceptionHandler tanımlıysa,
//
//Spring daha özelleşmiş olanı çağırır: Bu durumda NullPointerException'a özel metodu.
