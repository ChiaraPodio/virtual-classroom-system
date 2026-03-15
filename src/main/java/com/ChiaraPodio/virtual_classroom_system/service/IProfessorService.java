package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.ProfessorProfileUpdateRequestDto;
import com.ChiaraPodio.virtual_classroom_system.dto.ProfessorRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Professor;

import java.util.List;
import java.util.Optional;

public interface IProfessorService {

    public List<Professor> findAll();
    public Optional<Professor> findById(Long id);
    public  void saveProfessor(Professor professor);
    public void update(Long professor_id, ProfessorRequestDto professorRequestDto);
    public void profileUpdate (Long professor_id, ProfessorProfileUpdateRequestDto profileUpdateRequestDto);
    public  void deleteById(Long id);
    public Professor createProfessor (ProfessorRequestDto professorRequestDto);

}
