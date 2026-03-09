package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.CourseRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Course;
import com.ChiaraPodio.virtual_classroom_system.model.Student;
import com.ChiaraPodio.virtual_classroom_system.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IProfessorService professorService;

    @Autowired
    private IStudentService studentService;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void updateCourse(Long course_id, CourseRequestDto courseRequestDto) {
        Course newCourse = this.getCourseById(course_id);

        if (courseRequestDto.getName()!= null){
            newCourse.setName(courseRequestDto.getName());
        }
        if (courseRequestDto.getProfessor_id()!=null){
            newCourse.setProfessor(professorService.getProfessorById(courseRequestDto.getProfessor_id()));
        }
        if(courseRequestDto.getStudents_id()!=null) {
            List<Student> studentsList = new ArrayList<>();

            for (Long studentId : courseRequestDto.getStudents_id()) {
                Student student = studentService.getStudentById(studentId);
                studentsList.add(student);
            }

            newCourse.setStudents(studentsList);
        }

        this.saveCourse(newCourse);
    }

    @Override
    public void createCourse (CourseRequestDto courseRequestDto) {
        Course course = new Course();

        course.setName(courseRequestDto.getName());
        course.setProfessor(professorService.getProfessorById(courseRequestDto.getProfessor_id()));

        List<Student> studentsList = new ArrayList<>();

        for (Long studentId : courseRequestDto.getStudents_id()) {
            Student student = studentService.getStudentById(studentId);
            studentsList.add(student);
        }

        course.setStudents(studentsList);

        this.saveCourse(course);
    }

}
