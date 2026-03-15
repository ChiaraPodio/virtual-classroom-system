package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.CourseRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    public List<Course> findAll();
    public Optional<Course> findById(Long id);
    public void save(Course course);
    public void deleteById(Long id);
    public void update(Long course_id, CourseRequestDto courseRequestDto);
   public Course createCourse (CourseRequestDto courseRequestDto);

}
