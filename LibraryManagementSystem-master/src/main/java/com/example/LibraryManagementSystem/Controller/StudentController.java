package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Entity.Student;
import com.example.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Student-data")
public class StudentController {


    @Autowired
    StudentService obj;

    @PostMapping("/Add-Student")
   public String addStudent(@RequestBody Student student){
        obj.addStudent(student);
        return "Adding Student SuccessFully";
    }


}
