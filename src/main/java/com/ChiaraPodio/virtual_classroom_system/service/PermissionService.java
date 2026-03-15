package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.PermissionRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Permission;
import com.ChiaraPodio.virtual_classroom_system.repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deleteById(Long id) {
        if(!permissionRepository.existsById(id)){
            throw new RuntimeException("Permission not found");
        }

        permissionRepository.deleteById(id);
    }

    @Override
    public void update(Long permission_id, PermissionRequestDto permissionRequest) {
        Permission permission = this.findById(permission_id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));

        permission.setPermissionName(permissionRequest.getPermissionName());
        permissionRepository.save(permission);
    }

    @Override
    public Permission createPermision (PermissionRequestDto permissionRequest) {
        Permission permission = new Permission();

        permission.setPermissionName(permissionRequest.getPermissionName());
        return permissionRepository.save(permission);
    }

}
