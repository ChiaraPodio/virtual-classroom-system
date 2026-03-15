package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.PermissionRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    List<Permission> findAll();
    Optional<Permission> findById(Long id);
    Permission save(Permission permission);
    void deleteById(Long id);
    void update(Long permission_id, PermissionRequestDto permissionRequest);
    Permission createPermision (PermissionRequestDto permissionRequest);

}
