package com.example.pp_3_2.project.Controller;

import com.example.pp_3_2.project.Models.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    public UserController() {
    }

    @GetMapping(value = {"/user"})
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String user(@CurrentSecurityContext(expression = "authentication.principal") User principal, Model model) {
        model.addAttribute("user", principal);
        return "user";
    }
}