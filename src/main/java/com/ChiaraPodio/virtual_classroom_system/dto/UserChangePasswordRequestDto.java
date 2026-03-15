package com.ChiaraPodio.virtual_classroom_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserChangePasswordRequestDto {

    private String username;
    private String currentPassword;
    private String newPassword;

}
