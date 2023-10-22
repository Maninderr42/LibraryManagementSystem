package com.example.LibraryManagementSystem.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Table(name = "Student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int StudentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(unique = true, nullable = false)
    private String mobileNo;

    @Column(nullable = false)
    private String email;

    private String Address;


}
