package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.StudentProfileUpdateRequestDto;
import com.ChiaraPodio.virtual_classroom_system.dto.StudentRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Course;
import com.ChiaraPodio.virtual_classroom_system.model.Student;
import com.ChiaraPodio.virtual_classroom_system.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    private final IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//    @Autowired
//    private ICourseService courseService;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void update (Long student_id, StudentRequestDto studentRequestDto) {
        Student newStudent = this.findById(student_id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (studentRequestDto.getName() != null) {
            newStudent.setName(studentRequestDto.getName());
        }
        if (studentRequestDto.getEmail() != null) {
            newStudent.setEmail(studentRequestDto.getEmail());
        }
//        if (studentRequestDto.getCourses_id() != null) {
//            List<Course> courses = new ArrayList<>();
//
//            for (Long idCourse : studentRequestDto.getCourses_id()) {
//                Course course = courseService.findById(idCourse)
//                        .orElseThrow(() -> new RuntimeException("Course not found"));
//                course.getStudents().add(newStudent);
//                courses.add(course);
//            }
//
//            newStudent.setCourses(courses);
//        }
        studentRepository.save(newStudent);
    }

    @Override
    public void profileUpdate (Long student_id, StudentProfileUpdateRequestDto studentProfileUpdate) {

    Student newStudent = this.findById(student_id)
            .orElseThrow(() -> new RuntimeException("Student not found"));

    if (studentProfileUpdate.getName() != null) {
        newStudent.setName(studentProfileUpdate.getName());
    }
    if (studentProfileUpdate.getEmail() != null) {
        newStudent.setEmail(studentProfileUpdate.getEmail());
    }

    studentRepository.save(newStudent);
}

    @Override
    public void deleteById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Student createStudent (StudentRequestDto studentRequestDto) {
        Student student = new Student();

        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());

        List<Course> courses = new ArrayList<>();

//        for (Long idCourse : studentRequestDto.getCourses_id()) {
//            Course course = courseService.findById(idCourse)
//                    .orElseThrow(() -> new RuntimeException("Course not found"));
//            courses.add(course);
//        }

        student.setCourses(courses);
        return studentRepository.save(student);
    }
}
