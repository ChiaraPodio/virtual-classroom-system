package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.ProfessorRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Professor;

import java.util.List;

public interface IProfessorService {

    public List<Professor> getAllProfessors();
    public  Professor getProfessorById(Long id);
    public  void saveProfessor(Professor professor);
    public void editProfessor(Long professor_id, ProfessorRequestDto professorRequestDto);
    public  void deleteProfessor(Long id);
    public void createProfessor(ProfessorRequestDto professorRequestDto);

}
