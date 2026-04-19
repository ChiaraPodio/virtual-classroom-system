package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.UserChangePasswordRequestDto;
import com.ChiaraPodio.virtual_classroom_system.dto.UserRequestDto;
import com.ChiaraPodio.virtual_classroom_system.dto.UserUpdateRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Role;
import com.ChiaraPodio.virtual_classroom_system.model.UserSec;
import com.ChiaraPodio.virtual_classroom_system.repository.IUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleService roleService;

    public UserService(IUserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       IRoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

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

        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }

    @Override
    public void update(Long user_id, UserUpdateRequestDto userUpdateRequest) {
        UserSec user = this.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userUpdateRequest.getUsername()!=null) {
            user.setUsername(userUpdateRequest.getUsername());
        }

        if (userUpdateRequest.getEnabled() != null) {
            user.setEnabled(userUpdateRequest.getEnabled());
        }
        if (userUpdateRequest.getAccountNotExpired() != null) {
            user.setAccountNotExpired(userUpdateRequest.getAccountNotExpired());
        }
        if (userUpdateRequest.getAccountNotLocked() != null) {
            user.setAccountNotLocked(userUpdateRequest.getAccountNotLocked());
        }
        if (userUpdateRequest.getCredentialNotExpired() != null){
            user.setCredentialNotExpired(userUpdateRequest.getCredentialNotExpired());
        }
        if (userUpdateRequest.getRolesIdList()!=null) {
            Set<Role> rolesList = new HashSet<>();

            for (Long role_id : userUpdateRequest.getRolesIdList()) {
                Role role = roleService.findById(role_id)
                        .orElseThrow(() -> new RuntimeException("Role not found"));
                rolesList.add(role);
            }
            user.setRolesList(rolesList);
        }
        userRepository.save(user);
    }

    public void changePassword(UserChangePasswordRequestDto userChangePassword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        UserSec user = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(userChangePassword.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Current password incorrect");
        }

        user.setPassword(passwordEncoder.encode(userChangePassword.getNewPassword()));
        this.save(user);

    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public UserSec createUser(UserRequestDto userRequestDto) {
        UserSec user = new UserSec();

        user.setUsername(userRequestDto.getUsername());
        user.setPassword(this.encriptPassword(userRequestDto.getPassword()));
        user.setEnabled(userRequestDto.getEnabled());
        user.setAccountNotExpired(userRequestDto.getAccountNotExpired());
        user.setAccountNotLocked(userRequestDto.getAccountNotLocked());
        user.setCredentialNotExpired(userRequestDto.getCredentialNotExpired());

        Set<Role> rolesList = new HashSet<>();

        for (Long role_id : userRequestDto.getRolesIdList()) {
            Role role = roleService.findById(role_id)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            rolesList.add(role);
        }
        user.setRolesList(rolesList);
        return userRepository.save(user);
    }

}
