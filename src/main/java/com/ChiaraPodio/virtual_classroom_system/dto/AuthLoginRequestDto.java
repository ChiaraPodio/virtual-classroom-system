package com.ChiaraPodio.virtual_classroom_system.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDto (@NotBlank String username, @NotBlank String password) {
}
