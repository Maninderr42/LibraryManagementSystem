package com.example.LibraryManagementSystem.Service;


import com.example.LibraryManagementSystem.Entity.LibraryCard;
import com.example.LibraryManagementSystem.Entity.Student;
import com.example.LibraryManagementSystem.Enum.Card;
import com.example.LibraryManagementSystem.Repository.CardRepository;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public LibraryCard generatePlainCard(){

        LibraryCard libraryCard=new LibraryCard();

        libraryCard.setCardEnum(Card.NEW);

        libraryCard=cardRepository.save(libraryCard);

        return libraryCard;

    }

    public String associateWithStudent(Integer studnetId, Integer cardNo){

        Optional<Student> studentOptional=studentRepository.findById(studnetId);
        Student student=studentOptional.get();

        Optional<LibraryCard> optionalLibraryCard=cardRepository.findById(cardNo);
        LibraryCard libraryCard=optionalLibraryCard.get();

        libraryCard.setCardEnum(Card.ACTIVE);
        libraryCard.setNameOnCard(student.getName());
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);

        studentRepository.save(student);

        return "Card with "+cardNo+" has been associated to student with "+studnetId;



    }
}
