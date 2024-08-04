package com.example.techeazy.services;

import com.example.techeazy.entities.Student;
import com.example.techeazy.entities.Subject;
import com.example.techeazy.repository.StudentRepository;
import com.example.techeazy.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    public String addSubject(Subject details) {
        subjectRepository.save(details);
        return "Subject Successfully added";


    }

    public List<Subject> getAllSubjects() {
        ArrayList<Subject> subject=new ArrayList<>();
        Iterable<Subject> itr=subjectRepository.findAll();
        Iterator<Subject> iterator =itr.iterator();
        while(iterator.hasNext())
        {
            Subject s=iterator.next();
            subject.add(s);

        }
      //  System.out.println(student);
        return subject;
    }
}
