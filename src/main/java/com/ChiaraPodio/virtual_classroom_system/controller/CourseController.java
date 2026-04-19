package com.ChiaraPodio.virtual_classroom_system.controller;

import com.ChiaraPodio.virtual_classroom_system.dto.CourseRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Course;
import com.ChiaraPodio.virtual_classroom_system.service.ICourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT', 'PROFESSOR')")
    @GetMapping
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT', 'PROFESSOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {

        return courseService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody CourseRequestDto courseRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {

        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable Long id,
                                             @RequestBody CourseRequestDto courseRequest) {
        courseService.update(id, courseRequest);
        return ResponseEntity.noContent().build();
    }

}
