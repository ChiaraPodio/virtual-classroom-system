package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.StudentProfileUpdateRequestDto;
import com.ChiaraPodio.virtual_classroom_system.dto.StudentRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {

    public List<Student> findAll();
    public Optional<Student> findById(Long id);
    public void save(Student student);
    public void update (Long student_id, StudentRequestDto studentRequestDto);
    public void profileUpdate (Long student_id, StudentProfileUpdateRequestDto studentProfileUpdate);
    public void deleteById(Long id);
    public Student createStudent (StudentRequestDto studentRequestDto);

}
