package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.StudentRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Course;
import com.ChiaraPodio.virtual_classroom_system.model.Student;
import com.ChiaraPodio.virtual_classroom_system.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private ICourseService courseService;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void editStudent (Long student_id, StudentRequestDto studentRequestDto) {
        Student newStudent = this.getStudentById(student_id);

        if (studentRequestDto.getName() != null) {
            newStudent.setName(studentRequestDto.getName());
        }
        if (studentRequestDto.getEmail() != null) {
            newStudent.setEmail(studentRequestDto.getEmail());
        }
        if (studentRequestDto.getCourses_id() != null) {
            List<Course> courses = new ArrayList<>();

            for (Long idCourse : studentRequestDto.getCourses_id()) {
                Course course = courseService.getCourseById(idCourse);
                course.getStudents().add(newStudent);
                courses.add(course);
            }

            newStudent.setCourses(courses);
        }
        this.saveStudent(newStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void createStudent (StudentRequestDto studentRequestDto) {
        Student student = new Student();

        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());

        List<Course> courses = new ArrayList<>();

        for (Long idCourse : studentRequestDto.getCourses_id()) {
            Course course = courseService.getCourseById(idCourse);
            courses.add(course);
        }

        student.setCourses(courses);
        this.saveStudent(student);
    }
}
