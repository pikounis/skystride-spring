package com.sky.skystride.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://35.176.106.162", "http://localhost:3000"})
public class LoginController {

    @PostMapping("/login")
    public String login() {
        return "login";
    }
}