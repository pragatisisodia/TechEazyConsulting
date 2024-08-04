package com.example.techeazy.controller;

import com.example.techeazy.entities.Student;

import com.example.techeazy.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student details)
    {
        return studentService.addStudent(details);
    }
    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents()
    {
        return studentService.getAllStudents();
    }

    @PostMapping("/enroll")
    public Optional<Student> enrollStudentInSubject(@RequestParam Integer studentId, @RequestParam Integer subjectId) {
        return studentService.enrollStudentInSubject(studentId, subjectId);
    }




}
