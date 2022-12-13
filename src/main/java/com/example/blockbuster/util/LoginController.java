package com.example.blockbuster.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
