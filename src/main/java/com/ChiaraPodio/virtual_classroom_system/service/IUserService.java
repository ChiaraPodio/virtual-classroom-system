package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.UserChangePasswordRequestDto;
import com.ChiaraPodio.virtual_classroom_system.dto.UserRequestDto;
import com.ChiaraPodio.virtual_classroom_system.dto.UserUpdateRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.UserSec;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<UserSec> findAll();
    public Optional<UserSec> findById(Long id);
    public UserSec save(UserSec userSec);
    public void deleteById(Long id);
    public void update(Long user_id, UserUpdateRequestDto userUpdateRequest);
    public void changePassword(UserChangePasswordRequestDto userChangePassword);
    public String encriptPassword(String password);
    public UserSec createUser(UserRequestDto userRequestDto);

}
