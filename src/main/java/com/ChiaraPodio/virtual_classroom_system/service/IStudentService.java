package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.StudentRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Student;

import java.util.List;

public interface IStudentService {

    public List<Student> getAllStudents();
    public Student getStudentById(Long id);
    public void saveStudent(Student student);
    public void editStudent (Long student_id, StudentRequestDto studentRequestDto);
    public void deleteStudent(Long id);
    public void createStudent (StudentRequestDto studentRequestDto);

}
