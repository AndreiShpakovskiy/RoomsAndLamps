package com.shpakovskiy.roomsandlamps.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    private String testMethod() {
        return "Test time 14:32";
    }

    @GetMapping("roomsandlamps.herokuapp.com/")
    private String testMethod1() {
        return "Test time 14:32111";
    }
}
