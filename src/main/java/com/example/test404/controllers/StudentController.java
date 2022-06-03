package com.example.test404.controllers;


import com.example.test404.config.SecurityConfig;
import com.example.test404.exception.ResourceNotFoundException;
import com.example.test404.models.Person;
import com.example.test404.models.Student;
import com.example.test404.repository.PersonRepository;
import com.example.test404.repository.StudentRepository;
import com.example.test404.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/students")

public class StudentController {

    @Value("${filename}")
    private String filename;
    @Autowired
    private StudentService studentService;

    @GetMapping()
    @PreAuthorize("hasAuthority('persons:read')")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('persons:read')")
    public Student createStudent(@RequestBody Student student) {
        Student newStudent= studentService.createStudent(student);
        studentService.writerToFile(filename);
        return newStudent;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('persons:read')")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('persons:write')")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student studentDetails) {
        ResponseEntity<Student> updateStudent = studentService.updateStudent(id, studentDetails);
        studentService.writerToFile(filename);
        return updateStudent;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('persons:write')")
    public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable long id) {
        ResponseEntity<Map<String, Boolean>> deleteStudent = studentService.deleteStudent(id);
        studentService.writerToFile(filename);
        return deleteStudent;
    }

    @GetMapping("/groups")
    public List<String> getGroup(){
        return studentService.getGroup();
    }

    @GetMapping("/pwd")
        public String getRandomPass(){
            return studentService.getRandomPassword(10);
        }
}

