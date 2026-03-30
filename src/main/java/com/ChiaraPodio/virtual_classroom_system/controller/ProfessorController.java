package com.ChiaraPodio.virtual_classroom_system.controller;

import com.ChiaraPodio.virtual_classroom_system.dto.ProfessorProfileUpdateRequestDto;
import com.ChiaraPodio.virtual_classroom_system.dto.ProfessorRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Professor;
import com.ChiaraPodio.virtual_classroom_system.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    @GetMapping
    public ResponseEntity<List<Professor>> findAll() {

        return ResponseEntity.ok(professorService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Long id) {

        return professorService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Professor> saveProfessor(@RequestBody ProfessorRequestDto professorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.createProfessor(professorRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> editProfessor(@PathVariable Long id,
                              @RequestBody ProfessorRequestDto professorRequest) {
        professorService.update(id, professorRequest);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('PROFESSOR')")
    @PutMapping("/profile/{id}")
    public ResponseEntity<Void> editProfessorProfile(@PathVariable Long id,
                                                     @RequestBody ProfessorProfileUpdateRequestDto profileUpdateRequestDto) {
        professorService.profileUpdate(id, profileUpdateRequestDto);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {

        professorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
