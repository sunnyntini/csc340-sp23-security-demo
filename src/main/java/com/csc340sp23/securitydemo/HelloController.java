package com.csc340sp23.securitydemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author sunny
 */
@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String adminHello() {
        return "admin";
    }
    
    @GetMapping("/mod")
    public String modHello() {
        return "mod";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/403")
    public String _403() {
        return "403";
    }
}
