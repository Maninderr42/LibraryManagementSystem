package com.example.LibraryManagementSystem.Entity;

import com.example.LibraryManagementSystem.Enum.Card;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "CardStatus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardStatus {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CardNo;

     @Enumerated(value = EnumType.STRING)
     private Card cardEnum;

     private Card card;

    @OneToOne
    @JoinColumn
    private  Student student;
}
