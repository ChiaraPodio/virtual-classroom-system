package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.CourseRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Course;
import com.ChiaraPodio.virtual_classroom_system.model.Professor;
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

            Professor oldProfessor = newCourse.getProfessor();

            Professor newProfessor = professorService.findById(courseRequestDto.getProfessor_id()).
                    orElseThrow(() -> new RuntimeException("Professor not found"));

            if (oldProfessor != null) {
                oldProfessor.getCourses().remove(newCourse);
            }

            newCourse.setProfessor(newProfessor);

            if (!newProfessor.getCourses().contains(newCourse)) {
                newProfessor.getCourses().add(newCourse);
            }
        }
        if(courseRequestDto.getStudents_id()!=null) {

            if (newCourse.getStudents() != null) {
                for (Student student : newCourse.getStudents()) {
                    student.getCourses().remove(newCourse);
                }
            }

            List<Student> newStudentsList = new ArrayList<>();

            for (Long studentId : courseRequestDto.getStudents_id()) {
                Student student = studentService.findById(studentId)
                        .orElseThrow(() -> new RuntimeException("Student not found"));
                newStudentsList.add(student);
                if (!student.getCourses().contains(newCourse)) {
                    student.getCourses().add(newCourse);
                }
            }

            newCourse.setStudents(newStudentsList);
        }

        courseRepository.save(newCourse);
    }

    @Override
    public Course createCourse (CourseRequestDto courseRequestDto) {
        Course course = new Course();

        course.setName(courseRequestDto.getName());

        Professor professor = professorService.findById(courseRequestDto.getProfessor_id()).
                orElseThrow(() -> new RuntimeException("Professor not found"));
        course.setProfessor(professor);
        professor.getCourses().add(course);//trabajando en memoria, despues se persiste solo

        List<Student> studentsList = new ArrayList<>();

        for (Long studentId : courseRequestDto.getStudents_id()) {
            Student student = studentService.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            studentsList.add(student);
            student.getCourses().add(course);
        }

        course.setStudents(studentsList);

        return courseRepository.save(course);
    }

}
