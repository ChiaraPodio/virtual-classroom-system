package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.CourseRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Course;
import com.ChiaraPodio.virtual_classroom_system.model.Student;
import com.ChiaraPodio.virtual_classroom_system.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IProfessorService professorService;

    @Autowired
    private IStudentService studentService;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteById(Long id) {
        if(!courseRepository.existsById(id)){
            throw new RuntimeException("Course not found");
        }
        courseRepository.deleteById(id);
    }

    @Override
    public void update(Long course_id, CourseRequestDto courseRequestDto) {
        Course newCourse = this.findById(course_id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (courseRequestDto.getName()!= null){
            newCourse.setName(courseRequestDto.getName());
        }
        if (courseRequestDto.getProfessor_id()!=null){
            newCourse.setProfessor(professorService.findById(courseRequestDto.getProfessor_id()).
                    orElseThrow(() -> new RuntimeException("Professor not found")));
        }
        if(courseRequestDto.getStudents_id()!=null) {
            List<Student> studentsList = new ArrayList<>();

            for (Long studentId : courseRequestDto.getStudents_id()) {
                Student student = studentService.findById(studentId)
                        .orElseThrow(() -> new RuntimeException("Student not found"));
                studentsList.add(student);
            }

            newCourse.setStudents(studentsList);
        }

        courseRepository.save(newCourse);
    }

    @Override
    public Course createCourse (CourseRequestDto courseRequestDto) {
        Course course = new Course();

        course.setName(courseRequestDto.getName());
        course.setProfessor(professorService.findById(courseRequestDto.getProfessor_id()).
                orElseThrow(() -> new RuntimeException("Professor not found")));

        List<Student> studentsList = new ArrayList<>();

        for (Long studentId : courseRequestDto.getStudents_id()) {
            Student student = studentService.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            studentsList.add(student);
        }

        course.setStudents(studentsList);

        return courseRepository.save(course);
    }

}
