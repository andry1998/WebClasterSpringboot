package com.example.test404.service;

import com.example.test404.config.SecurityConfig;
import com.example.test404.exception.ResourceNotFoundException;
import com.example.test404.models.Person;
import com.example.test404.models.Student;
import com.example.test404.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SecurityConfig securityConfig;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    public ResponseEntity<Student> getStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not exist with id:" + id));
        return ResponseEntity.ok(student);
    }

    public ResponseEntity<Student> updateStudent(long id, Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not exist with id:" + id));
        student.setFirst_name(studentDetails.getFirst_name());
        student.setLast_name(studentDetails.getLast_name());
        student.setEmail(studentDetails.getEmail());
        student.setPassword(securityConfig.passwordEncoder().encode(studentDetails.getPassword()));
        student.setStudy_group(studentDetails.getStudy_group());


        Student updateStudent = studentRepository.save(student);
        return ResponseEntity.ok(updateStudent);
    }

    public ResponseEntity<Map<String, Boolean>> deleteStudent(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
        studentRepository.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    public List<String> getGroup(){
        return studentRepository.getStudyGroup();
    }

    public void writerToFile(String filename){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))){
            for(Student student : studentRepository.findAll()){
                bufferedWriter.write(student + "\n");
            }
        } catch(IOException e){
            e.printStackTrace();
        }
//
//        try (BufferedWriter writter = new BufferedWriter(new FileWriter(filename))) {
//			for (Student student : studentRepository.findAll()) {
//				writter.write(student + "\n");
//			}
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
    }

}
