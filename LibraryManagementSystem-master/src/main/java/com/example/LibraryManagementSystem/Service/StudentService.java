package com.example.LibraryManagementSystem.Service;


import com.example.LibraryManagementSystem.Entity.Student;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository obj;

    public void addStudent(Student student){

        obj.save(student);
    }

}
