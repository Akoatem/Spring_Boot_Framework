package com.akoatem.micro.services;

import com.akoatem.micro.models.Student;
import com.akoatem.micro.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudent(){
        return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        // to check if email exist
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email exist");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    // delete api
    public void deleteStudent(Long studentid) {
        // check for student id exist
        boolean exist = studentRepository.existsById(studentid);
        if(!exist){
            throw new IllegalStateException("student with id"
                    + studentid + "does not exit");
        }
        studentRepository.deleteById(studentid);

    }

    // update method
    @Transactional
    public void updateStudent(Long studentid, String name, String email) {
        Student student = (Student) studentRepository.findAllById(studentid)
                .orElseThrow(() -> new IllegalStateException("student with id" + studentid + "does not exist"));
                if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
                    student.setName(name);
                }
               if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
                   student.setEmail(email);
                   Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
                   if(studentOptional.isPresent()){
                       throw  new IllegalStateException("email taken");
                   }
                   student.setEmail(email);
        }
    }
}
