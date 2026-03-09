package com.ChiaraPodio.virtual_classroom_system.service;

import com.ChiaraPodio.virtual_classroom_system.dto.CourseRequestDto;
import com.ChiaraPodio.virtual_classroom_system.model.Course;

import java.util.List;

public interface ICourseService {

    public List<Course> getAllCourses();
    public Course getCourseById(Long id);
    public void saveCourse(Course course);
    public void deleteCourse(Long id);
    public void updateCourse(Long course_id, CourseRequestDto courseRequestDto);
   public void createCourse (CourseRequestDto courseRequestDto);

}
