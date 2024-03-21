package com.example.pp_3_2.project.Controller;



import com.example.pp_3_2.project.Models.User;
import com.example.pp_3_2.project.Service.RoleService;
import com.example.pp_3_2.project.Service.UserServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@Controller
@RequestMapping(name = "/admin")
public class AdminController {

    private UserServiceImp userService;


    private RoleService roleService;

    public AdminController(UserServiceImp userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String displayAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/admin/addUser")
    public String displayNewUserForm(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("headerMessage", "Добавить пользователя");
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PatchMapping("/admin/editUser")
    public String updateUsers(@ModelAttribute("user") User user, @RequestParam(value = "nameRoles", required = false) String[] roles) {
        userService.getUserAndRoles(user, roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/editUser")
    public String displayEditUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("headerMessage", "Изменить пользователя");
        model.addAttribute("user", user);
        return "editUser";
    }


    @PostMapping("/admin/addUser")
    String create(@ModelAttribute("user") User user, @RequestParam(value = "nameRoles", required = false) String[] roles) {
        userService.getNotNullRole(user);
        userService.getUserAndRoles(user, roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/deleteUser")
    public String deleteUserById(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}