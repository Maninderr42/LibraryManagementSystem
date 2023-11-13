package com.example.LibraryManagementSystem.Entity;


import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer TransactionId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    private Date returnDate;

    private Integer fine;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date lastModified;

    @OneToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private LibraryCard card;



}
