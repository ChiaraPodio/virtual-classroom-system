package com.ChiaraPodio.virtual_classroom_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDto {

    private String name;
    private Long professor_id;
    private List<Long> students_id;

}
