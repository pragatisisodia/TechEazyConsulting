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
import java.util.Optional;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;
    public String addStudent(Student details) {
        studentRepository.save(details);
        return "Student Successfully added";


    }

    public List<Student> getAllStudents() {
       ArrayList<Student> student=new ArrayList<>();
       Iterable<Student> itr=studentRepository.findAll();
        Iterator<Student> iterator =itr.iterator();
       while(iterator.hasNext())
       {
           Student s=iterator.next();
           student.add(s);

       }
        System.out.println(student);
       return student;
    }


    public Optional<Student> enrollStudentInSubject(Integer studentId, Integer subjectId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Subject> subjectOpt = subjectRepository.findById(subjectId);

        if (studentOpt.isPresent() && subjectOpt.isPresent()) {
            Student student = studentOpt.get();
            Subject subject = subjectOpt.get();
            student.getSubjects().add(subject);
            subject.getStudents().add(student);
            studentRepository.save(student);
            subjectRepository.save(subject);
            return Optional.of(student);
        }
        return Optional.empty();
    }

}
