package com.ChiaraPodio.virtual_classroom_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorRequestDto {

    private String name;
    private String email;
    // private List<Long> courses_id;

}
