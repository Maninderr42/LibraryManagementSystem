package com.example.LibraryManagementSystem.Entity;

import com.example.LibraryManagementSystem.Enum.Card;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CardStatus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryCard {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CardNo;

     @Enumerated(value = EnumType.STRING)
     private Card cardEnum;

     private String nameOnCard;

     private Integer noOfBooksIssued;

    @OneToOne
    @JoinColumn
    private  Student student;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Transaction>transactionList=new ArrayList<>();
}
