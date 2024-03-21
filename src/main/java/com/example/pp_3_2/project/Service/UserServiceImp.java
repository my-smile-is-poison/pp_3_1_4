package com.example.pp_3_2.project.Service;


import com.example.pp_3_2.project.Models.Role;
import com.example.pp_3_2.project.Models.User;
import com.example.pp_3_2.project.Repositories.RoleRepository;
import com.example.pp_3_2.project.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserDetailsService {


    @Autowired
    UserRepository repository;

    private final RoleService roleService;

    @Autowired
    @Lazy
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserServiceImp(UserRepository repository, RoleService roleService) {
        super();
        this.repository = repository;
        this.roleService = roleService;
    }


    public List<User> getAllUsers() {
        return repository.findAll();
    }


    public User getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public void saveUser(User user) {


        //user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public User getUserAndRoles(User user, String[] roles) {
        if (roles == null) {
            user.setRoles(roleService.getRoleByName(new String[]{"ROLE_USER"}));
        } else {
            user.setRoles(roleService.getRoleByName(roles));
        }
        return user;
    }
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
    public User getNotNullRole(User user) {
        if (user.getRoles() == null) {
            user.setRoles(Collections.singleton(new Role(2L)));
        }
        return user;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

}
