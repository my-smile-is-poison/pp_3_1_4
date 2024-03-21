package com.example.pp_3_2.project.Service;

import com.example.pp_3_2.project.Models.Role;


import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();

    Set<Role> getRoleByName(String[] roleName);
}