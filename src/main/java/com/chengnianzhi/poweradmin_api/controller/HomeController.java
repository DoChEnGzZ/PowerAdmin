package com.chengnianzhi.poweradmin_api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Value("${welcome.content}")
    private String welcome;

    @RequestMapping("/")
    public String home() {
        System.out.println(this.welcome);
        return "";
    }
}
