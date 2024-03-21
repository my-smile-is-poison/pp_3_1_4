package com.example.pp_3_2.project.Service;

import com.example.pp_3_2.project.Models.User;

import java.util.List;

public interface UserService {

     List<User> getAllUsers();

     User getUserById(Long id);

     void saveUser(User user);

     User getUserAndRoles(User user, String[] roles);


     void deleteUser(Long id);

     User getNotNullRole(User user);
}