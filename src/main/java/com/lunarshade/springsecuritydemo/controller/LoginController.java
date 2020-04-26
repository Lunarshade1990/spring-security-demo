package com.lunarshade.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "fancy-login";
    }
    @GetMapping("/accessDenied")
    public String showaccessDeniedPage() {
        return "access-denied";
    }
}