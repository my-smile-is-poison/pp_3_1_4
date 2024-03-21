package com.example.pp_3_2.project.Service;

import com.example.pp_3_2.project.Models.Role;
import com.example.pp_3_2.project.Repositories.RoleRepository;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Set<Role> getRoleByName(String[] roleName) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roleName) {
            roleSet.add(roleRepository.findRoleByName(role));
        }
        return roleSet;
    }

}