package com.findjob_system.Services.RoleService;


import com.findjob_system.Exceptions.NewRoleAlreadyExistsException;
import com.findjob_system.Exceptions.UserAlreadyExistsException;
import com.findjob_system.Exceptions.UserNotFoundException;
import com.findjob_system.models.Role;
import com.findjob_system.models.User;
import com.findjob_system.repository.RoleRepository;
import com.findjob_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor //this annotation says mean that only field with final key word will be injected
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    @Override
    public List<Role> getAllRoles() {
        return null;
    }

    @Override
    public Role createRole(Role role){
        Optional<Role> checkRole = roleRepository.findByName(role.getName());
        if (checkRole.isPresent()) {
            throw new NewRoleAlreadyExistsException(" '"+checkRole.get().getName() + "' Role already exists");
        }
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        this.removerAllUserFromRole(roleId);
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName).get();
    }

    @Override
    public Role findByRoleById(Long roleId) {
        return roleRepository.findById(roleId).get();
    }
    @Override
    public User removeUSerFromRole(Long userId, Long roleId) {
        Optional<User> user = userRepository.findByUserId(userId);
        Optional<Role> role = roleRepository.findById(roleId);

        if (role.isPresent() && role.get().getUsers().contains(user.get())){
            role.get().removeUSerFromRoles(user.get());
            roleRepository.save(role.get());
            return user.get();
        }
        throw new UserNotFoundException("user with name "+user.get().getUserName() + " Not found");
    }

    @Override
    public User assignUserToRole(Long userId, Long roleId) {
        Optional<User> user = userRepository.findByUserId(userId);
        Optional<Role> role = roleRepository.findById(roleId);

        if(user.isPresent() && user.get().getRoles().contains(role.get())){
            throw new UserAlreadyExistsException(user.get().getUserName() + " already assigned "+role.get().getName() +" role");
        }
        role.ifPresent(therole -> therole.assignUserToRole(user.get()));
        roleRepository.save(role.get());
        return user.get();
    }

    @Override
    public Role removerAllUserFromRole(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        role.ifPresent(Role::removellUSerFromRoles);
        return roleRepository.save(role.get());

    }
}
