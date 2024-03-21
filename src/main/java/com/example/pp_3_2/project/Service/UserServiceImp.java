package com.example.pp_3_2.project.Service;


import com.example.pp_3_2.project.Models.Role;
import com.example.pp_3_2.project.Models.User;
import com.example.pp_3_2.project.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public UserServiceImp(UserRepository repository, RoleService roleService) {
        this.repository = repository;
        this.roleService = roleService;

    }


    public List<User> getAllUsers() {
        return repository.findAll();
    }


    public User getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void saveUser(User user) {


        //user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
    @Transactional
    public User getUserAndRoles(User user, String[] roles) {
        if (roles == null) {
            user.setRoles(roleService.getRoleByName(new String[]{"ROLE_USER"}));
        } else {
            user.setRoles(roleService.getRoleByName(roles));
        }
        return user;
    }
    @Transactional
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public User getNotNullRole(User user) {
        if (user.getRoles() == null) {
            user.setRoles(Collections.singleton(new Role(2L)));
        }
        return user;
    }

}
