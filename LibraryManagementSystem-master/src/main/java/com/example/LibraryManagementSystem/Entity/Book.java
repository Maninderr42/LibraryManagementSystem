package com.example.LibraryManagementSystem.Entity;

import com.example.LibraryManagementSystem.Enum.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(nullable = false)
    private String bookName;

    private int price;

    private int noOfPages;

    private double rating;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private boolean isAvailable;

    @ManyToOne
    @JoinColumn
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();


}
