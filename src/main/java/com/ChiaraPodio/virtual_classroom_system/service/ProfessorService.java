package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.ProfessorProfileUpdateRequestDto;
import com.ChiaraPodio.virtual_classroom_system.dto.ProfessorRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Course;
import com.ChiaraPodio.virtual_classroom_system.model.Professor;
import com.ChiaraPodio.virtual_classroom_system.repository.IProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService implements IProfessorService {

    private final IProfessorRepository professorRepository;

    public ProfessorService(IProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Override
    public Optional<Professor> findById(Long id) {
        return professorRepository.findById(id);
    }

    @Override
    public void saveProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    @Override
    public void update(Long professor_id, ProfessorRequestDto professorRequestDto) {
        Professor newProfessor = this.findById(professor_id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        if (professorRequestDto.getName() != null) {
            newProfessor.setName(professorRequestDto.getName());
        }
        if (professorRequestDto.getEmail() != null) {
            newProfessor.setEmail(professorRequestDto.getEmail());
        }
//        if (professorRequestDto.getCourses_id() != null) {
//            List<Course> courses = new ArrayList<>();
//
//            for (Long idCourse : professorRequestDto.getCourses_id()) {
//                Course course = courseRepository.findById(idCourse)
//                        .orElseThrow(() -> new RuntimeException("Course not found"));
//                course.setProfessor(newProfessor);
//                courseRepository.save(course);
//                courses.add(course);
//            }
//
//            newProfessor.setCourses(courses);
//        }
        professorRepository.save(newProfessor);
    }

    @Override
    public void profileUpdate (Long professor_id, ProfessorProfileUpdateRequestDto profileUpdateRequestDto) {

        Professor newProfessor = this.findById(professor_id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        if (profileUpdateRequestDto.getName() != null) {
            newProfessor.setName(profileUpdateRequestDto.getName());
        }
        if (profileUpdateRequestDto.getEmail() != null) {
            newProfessor.setEmail(profileUpdateRequestDto.getEmail());
        }
        professorRepository.save(newProfessor);
    }

    @Override
    public void deleteById(Long id) {
        if(!professorRepository.existsById(id)){
            throw new RuntimeException("Professor not found");
        }
        professorRepository.deleteById(id);
    }

    @Override
    public Professor createProfessor(ProfessorRequestDto professorRequestDto) {
        Professor professor = new Professor();

        professor.setName(professorRequestDto.getName());
        professor.setEmail(professorRequestDto.getEmail());

        List<Course> courses = new ArrayList<>();

//        for (Long idCourse : professorRequestDto.getCourses_id()) {
//            Course course = courseRepository.findById(idCourse)
//                    .orElseThrow(() -> new RuntimeException("Course not found"));
//            course.setProfessor(professor);
//            courseRepository.save(course);
//            courses.add(course);
//        }

        professor.setCourses(courses);
        return professorRepository.save(professor);
    }

}

