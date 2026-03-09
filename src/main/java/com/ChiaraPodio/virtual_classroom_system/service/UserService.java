package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.UserRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Role;
import com.ChiaraPodio.virtual_classroom_system.model.UserSec;
import com.ChiaraPodio.virtual_classroom_system.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleService roleService;

    @Override
    public List<UserSec> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserSec> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserSec save(UserSec userSec) {
        return userRepository.save(userSec);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(UserSec userSec) {

        save(userSec);
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public void createUser(UserRequestDto userRequestDto) {
        UserSec user = new UserSec();

        user.setUsername(userRequestDto.getUsername());
        user.setPassword(this.encriptPassword(userRequestDto.getPassword()));
        user.setEnabled(userRequestDto.isEnabled());
        user.setAccountNotExpired(userRequestDto.isAccountNotExpired());
        user.setAccountNotLocked(userRequestDto.isAccountNotLocked());
        user.setCredentialNotExpired(userRequestDto.isCredentialNotExpired());

        Set<Role> rolesList = new HashSet<>();

        for (Long role_id : userRequestDto.getRolesIdList()) {
            Role role = roleService.findById(role_id)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            rolesList.add(role);
        }
        user.setRolesList(rolesList);
        this.save(user);

    }

}
