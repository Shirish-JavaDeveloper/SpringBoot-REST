package com.example.spring.rest.controller;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    @Autowired
    private MessageSource messageSource;


    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World !";
    }

    @GetMapping("/hello-world-bean/{message}")
    public HelloWorldBean helloWorldBean(@PathVariable String message){
        return new HelloWorldBean(message);
    }

    @GetMapping("/hello-world-i18")
    public String helloWorldi18(@RequestHeader(required = false, name = "Accept-Language", defaultValue = "us") Locale locale){
        return messageSource.getMessage("good.morning",null,locale);
    }

    @GetMapping("/hello-world-i18-generic")
    public String helloWorldi18(){
        return messageSource.getMessage("good.morning",null, LocaleContextHolder.getLocale());
    }

}
