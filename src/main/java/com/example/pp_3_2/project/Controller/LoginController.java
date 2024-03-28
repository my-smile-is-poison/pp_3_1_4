package com.example.pp_3_2.project.Controller;

import com.example.pp_3_2.project.Models.User;
import com.example.pp_3_2.project.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @RequestMapping(name = "/login")
    public String login() {
        return "login";
    }

}

