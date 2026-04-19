package com.ChiaraPodio.virtual_classroom_system.controller;

import com.ChiaraPodio.virtual_classroom_system.dto.UserChangePasswordRequestDto;
import com.ChiaraPodio.virtual_classroom_system.dto.UserRequestDto;
import com.ChiaraPodio.virtual_classroom_system.dto.UserUpdateRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.UserSec;
import com.ChiaraPodio.virtual_classroom_system.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserSec>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserSec> getUserById(@PathVariable Long id) {
        return userService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserSec> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
       userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateUser (@PathVariable Long id,
                                            @RequestBody UserUpdateRequestDto userUpdate) {
        userService.update(id, userUpdate);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/me/password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> changePassword (@RequestBody UserChangePasswordRequestDto userChangePassword) {
        userService.changePassword(userChangePassword);
        return ResponseEntity.noContent().build();
    }

}
