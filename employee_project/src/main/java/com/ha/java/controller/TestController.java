package com.ha.java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("testing")
    public String getString(){
        return "Congratulations !!!";
    }

}
