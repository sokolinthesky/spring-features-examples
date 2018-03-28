package com.example.interseptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleWebController {

    private static final Logger log = LoggerFactory.getLogger(SimpleWebController.class);

    @RequestMapping("/hello")
    public String hello(){
        log.info("Welcome to access RequestMapping: /hello!");
        return "Hello World!";
    }
}
