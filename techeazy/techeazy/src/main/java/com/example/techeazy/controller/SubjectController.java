package com.example.techeazy.controller;

import com.example.techeazy.entities.Student;
import com.example.techeazy.entities.Subject;
import com.example.techeazy.repository.SubjectRepository;
import com.example.techeazy.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping("/addSubject")
    public String addSubject(@RequestBody Subject details)
    {
        return subjectService.addSubject(details);
    }
    @GetMapping("/getAllSubjects")
    public List<Subject> getAllSubjects()
    {
        return subjectService.getAllSubjects();
    }

}
