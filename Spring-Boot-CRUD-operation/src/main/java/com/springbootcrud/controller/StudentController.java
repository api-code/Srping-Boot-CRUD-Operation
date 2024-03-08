package com.springbootcrud.controller;

import com.springbootcrud.entity.Student;
import com.springbootcrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired

    StudentRepository repository;


    //get all the student
    @GetMapping("/allStudents")
    public List<Student> getAllStudents(){
    //List<Student> students = repository.findAll();
        return repository.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id){
        return repository.findById(id).get();
    }


    //    creating new student
    @PostMapping("/student/add")
//    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student newStudent = repository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @PutMapping("/student/update/{id}")
    public Student updateStudent(@PathVariable int id){
        Student student = repository.findById(id).get();
        student.setName("monica");
        student.setPercentage(60);
        repository.save(student);
        return student;
    }

    @DeleteMapping("/student/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeStudent(@PathVariable int id){
        Student student = repository.findById(id).get();
        repository.delete(student);
    }
}
