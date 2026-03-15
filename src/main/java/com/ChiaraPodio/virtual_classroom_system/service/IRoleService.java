package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.RoleRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    List<Role> findAll();
    Optional<Role> findById(Long id);
    Role save(Role role);
    void deleteById(Long id);
    void update(Long role_id, RoleRequestDto roleRequest);
    public Role createRole (RoleRequestDto roleRequest);

}
