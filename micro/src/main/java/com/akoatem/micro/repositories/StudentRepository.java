package com.akoatem.micro.repositories;

import com.akoatem.micro.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // find student by email

    // This will transform from SQL
    // SELECT * FROM student WHERE email = ?
    @Query("SELECT s FROM Student  s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(Student email);

    Optional<Student> findStudentByEmail(String email);

    Optional<Object> findAllById(Long studentid);
}
