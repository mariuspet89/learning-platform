package eu.accesa.learningplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public String login() {
        return "signin";
    }

    @RequestMapping(value = "/home")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/")
    public String home1() {
        return "index";
    }

    @RequestMapping(value = "/ldap")
    public String ldap() {
        System.out.println("========= LDAP ===========");
        return "index";
    }
}
