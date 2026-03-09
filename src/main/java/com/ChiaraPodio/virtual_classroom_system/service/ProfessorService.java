package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.ProfessorRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Course;
import com.ChiaraPodio.virtual_classroom_system.model.Professor;
import com.ChiaraPodio.virtual_classroom_system.repository.IProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService implements IProfessorService {

    @Autowired
    private IProfessorRepository professorRepository;

    @Autowired
    private ICourseService courseService;

    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Override
    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    @Override
    public void editProfessor(Long professor_id, ProfessorRequestDto professorRequestDto) {
        Professor newProfessor = this.getProfessorById(professor_id);

        if (professorRequestDto.getName() != null) {
            newProfessor.setName(professorRequestDto.getName());
        }
        if (professorRequestDto.getEmail() != null) {
            newProfessor.setEmail(professorRequestDto.getEmail());
        }
        if (professorRequestDto.getCourses_id() != null) {
            List<Course> courses = new ArrayList<>();

            for (Long idCourse : professorRequestDto.getCourses_id()) {
                Course course = courseService.getCourseById(idCourse);
                course.setProfessor(newProfessor);
                courses.add(course);
            }

            newProfessor.setCourses(courses);
        }
        this.saveProfessor(newProfessor);
    }

    @Override
    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }

    @Override
    public void createProfessor(ProfessorRequestDto professorRequestDto) {
        Professor professor = new Professor();

        professor.setName(professorRequestDto.getName());
        professor.setEmail(professorRequestDto.getEmail());

        List<Course> courses = new ArrayList<>();

        for (Long idCourse : professorRequestDto.getCourses_id()) {
            Course course = courseService.getCourseById(idCourse);
            courses.add(course);
        }

        professor.setCourses(courses);
        this.saveProfessor(professor);
    }

}

