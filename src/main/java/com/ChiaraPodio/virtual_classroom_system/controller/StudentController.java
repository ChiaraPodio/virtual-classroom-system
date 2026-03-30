package com.ChiaraPodio.virtual_classroom_system.controller;

import com.ChiaraPodio.virtual_classroom_system.dto.StudentProfileUpdateRequestDto;
import com.ChiaraPodio.virtual_classroom_system.dto.StudentRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Student;
import com.ChiaraPodio.virtual_classroom_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    @GetMapping
    public ResponseEntity<List<Student>> findAll() {

        return ResponseEntity.ok(studentService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {

        return studentService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody StudentRequestDto studentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> editStudent(@PathVariable Long id,
                                              @RequestBody StudentRequestDto studentRequest) {
        studentService.update(id, studentRequest);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @PutMapping("/profile/{id}")
    public ResponseEntity<Void> editStudentProfile(@PathVariable Long id,
                                                   @RequestBody StudentProfileUpdateRequestDto studentProfileUpdate) {
        studentService.profileUpdate(id, studentProfileUpdate);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
