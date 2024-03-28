package com.example.pp_3_2.project.Service;

import com.example.pp_3_2.project.Models.Role;
import com.example.pp_3_2.project.Models.User;

import java.util.List;

public interface UserService {

     User findByUsername(String username);
     List<User> getUsers();
     User showUserById(Long id);
     void saveUser(User user);
     void deleteUserById(Long id);
     void updateUserById(Long id, User user);

     List<Role> findRoles();
}