package com.ChiaraPodio.virtual_classroom_system.repository;

import com.ChiaraPodio.virtual_classroom_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {

}
