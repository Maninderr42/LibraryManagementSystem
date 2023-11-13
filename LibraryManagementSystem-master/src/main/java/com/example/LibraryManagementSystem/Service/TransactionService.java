package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Entity.LibraryCard;
import com.example.LibraryManagementSystem.Entity.Transaction;
import com.example.LibraryManagementSystem.Enum.Card;
import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import com.example.LibraryManagementSystem.Exception.*;
import com.example.LibraryManagementSystem.Repository.BookRespository;
import com.example.LibraryManagementSystem.Repository.CardRepository;
import com.example.LibraryManagementSystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private BookRespository bookRespository;

    @Autowired
    private CardRepository cardRepository;

    private static final Integer MAX_LIMIT_OF_BOOKS = 3;

    private static final Integer FINE_PER_DAY = 5;



    public String issueBook(Integer bookId, Integer cardId) throws Exception{

        Transaction transaction=new Transaction();

        transaction.setTransactionStatus(TransactionStatus.PENDING);

        Optional<Book> bookOptional=bookRespository.findById(bookId);

        if(!bookOptional.isPresent()){
            throw  new BookNotFoundExcetion("Book Not Found Exception");
        }

        Book book=bookOptional.get();

        if(!book.isAvailable()){
            throw new BookNotAvailableException(("Book Is UnAvailable"));
        }


        Optional<LibraryCard> optionalCard=cardRepository.findById(cardId);

        if(!optionalCard.isPresent()){
            throw new LibraryCardException("Library card not Found Exception");
        }


        LibraryCard libraryCard=optionalCard.get();

        if(!libraryCard.getCardEnum().equals(Card.ACTIVE)){
            throw new CardStatusNotActiveException("Card Status Is Not Active");
        }

        if(libraryCard.getNoOfBooksIssued()==MAX_LIMIT_OF_BOOKS){
            throw new MaxBooksAlreadyIssuedException(MAX_LIMIT_OF_BOOKS+"Max Book Already Issued");
        }
        transaction.setTransactionStatus(TransactionStatus.ISSUED);

        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued()+1);

        book.setAvailable(false);

        transaction.setBook(book);

        transaction.setCard(libraryCard);

        book.getTransactionList().add(transaction);

        libraryCard.getTransactionList().add(transaction);

        repository.save(transaction);

        return "Book Issued Successfully";

    }

    public String returnBook(Integer bookId, Integer cardId){
        Book book=bookRespository.findById(bookId).get();

        LibraryCard card=cardRepository.findById(cardId).get();

        Transaction transaction=repository.findTransactionByBookAndCardAndTranactionStatus(book,card,TransactionStatus.ISSUED);
        Date issueDate=transaction.getCreatedOn();

        long milliSeconds=Math.abs(System.currentTimeMillis()-issueDate.getTime());
        Long days= TimeUnit.DAYS.convert(milliSeconds,TimeUnit.MICROSECONDS);

        int fineAmount=0;

        if(days>15){
            fineAmount=Math.toIntExact((days-15)*FINE_PER_DAY);
        }

        Transaction newTranaction=new Transaction();

        newTranaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newTranaction.setReturnDate(new Date());
        newTranaction.setFine(fineAmount);

        newTranaction.setBook(book);
        newTranaction.setCard(card);

        book.setAvailable(true);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);

        book.getTransactionList().add(newTranaction);
        card.getTransactionList().add(newTranaction);

        repository.save(newTranaction);

        return "Book has been returned...........";


    }
}
