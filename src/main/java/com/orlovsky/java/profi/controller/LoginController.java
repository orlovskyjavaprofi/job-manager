/******************************************
 Created by: RXSUBRAM a.k.a angoothachap
 Created on: 4/17/2019 10:43 AM
 ******************************************/

package com.orlovsky.java.profi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
