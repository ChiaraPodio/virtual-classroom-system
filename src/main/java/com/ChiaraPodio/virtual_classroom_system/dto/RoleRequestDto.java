package com.ChiaraPodio.virtual_classroom_system.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDto {

    private String role;
    private Set<Long> permissionsIdList = new HashSet<>();

}
