package com.example.spring.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World !";
    }

    @GetMapping("/hello-world-bean/{message}")
    public HelloWorldBean helloWorldBean(@PathVariable String message){
        return new HelloWorldBean(message);
    }

}
