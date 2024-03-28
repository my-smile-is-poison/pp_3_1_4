package com.example.pp_3_2.project.Service;


import com.example.pp_3_2.project.Models.Role;
import com.example.pp_3_2.project.Models.User;
import com.example.pp_3_2.project.Repositories.RoleRepository;
import com.example.pp_3_2.project.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImp implements UserService {
    private  UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Autowired
    public void setUserAndRoleRepository(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User showUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUserById(Long id, User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<Role> findRoles() {
        return roleRepository.findAll();
    }
}