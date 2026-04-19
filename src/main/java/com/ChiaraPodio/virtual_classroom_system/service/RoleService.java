package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.RoleRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Permission;
import com.ChiaraPodio.virtual_classroom_system.model.Role;
import com.ChiaraPodio.virtual_classroom_system.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService implements IRoleService {

    private final IRoleRepository roleRepository;
    private final IPermissionService permissionService;

    public RoleService(IRoleRepository roleRepository,
                       IPermissionService permissionService) {
        this.roleRepository = roleRepository;
        this.permissionService = permissionService;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {

        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        if(!roleRepository.existsById(id)){
            throw new RuntimeException("Role not found");
        }

        roleRepository.deleteById(id);
    }

    @Override
    public void update(Long role_id, RoleRequestDto roleRequest) {
        Role role = this.findById(role_id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        if (roleRequest.getRole()!=null) {
            role.setRole(roleRequest.getRole());
        }
        if (roleRequest.getPermissionsIdList()!=null) {
            Set<Permission> permissionsList = new HashSet<>();

            for (Long id_permission : roleRequest.getPermissionsIdList()) {
                Permission permission = permissionService.findById(id_permission)
                        .orElseThrow(() -> new RuntimeException("Permission not found"));
                permissionsList.add(permission);
            }
            role.setPermissionsList(permissionsList);
        }
        roleRepository.save(role);


    }

    @Override
    public Role createRole (RoleRequestDto roleRequest) {
        Role role = new Role();

        role.setRole(roleRequest.getRole());
        Set<Permission> permissionsList = new HashSet<>();

        for (Long id_permission : roleRequest.getPermissionsIdList()) {
            Permission permission = permissionService.findById(id_permission)
                    .orElseThrow(() -> new RuntimeException("Permission not found"));
            permissionsList.add(permission);
        }

        role.setPermissionsList(permissionsList);
        return roleRepository.save(role);
    }

}
