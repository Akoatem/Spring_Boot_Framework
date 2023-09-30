package com.akoatem.micro.controllers;

import com.akoatem.micro.models.Student;
import com.akoatem.micro.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@Controller
@RestController

// create the api
// get request api
@RequestMapping(path="api/v1/student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    // get request
    @GetMapping
    public List<Student> getStudent(){
        return studentService.getStudent();
    }

    // post or create new student
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentid}")
    public void deleteStudent(@PathVariable("studentid") Long studentid){
        studentService.deleteStudent(studentid);

    }

    // update api
    @PutMapping(path = "{studentid}")
    public void updateStudent(
            @PathVariable("studentid") Long studentid,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        studentService.updateStudent(studentid, name, email);
    }

}
