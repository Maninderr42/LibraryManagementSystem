package com.example.LibraryManagementSystem.Repository;


import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Entity.LibraryCard;
import com.example.LibraryManagementSystem.Entity.Transaction;
import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transactionStatus);

}
