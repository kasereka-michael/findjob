package com.findjob_system.Services.RoleService;


import com.findjob_system.models.Role;
import com.findjob_system.models.User;

import java.util.List;

public interface IRoleService {

    List<Role> getAllRoles();
    Role createRole(Role role);

    void deleteRole(Long roleId);

    Role findRoleByName(String roleName);

    Role findByRoleById(Long roleId);

    User removeUSerFromRole(Long userId, Long roleId);

    User assignUserToRole(Long userId, Long roleId);

    Role removerAllUserFromRole(Long roleId);




}
