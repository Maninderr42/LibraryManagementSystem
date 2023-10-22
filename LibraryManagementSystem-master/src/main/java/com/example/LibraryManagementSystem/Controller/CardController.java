package com.example.LibraryManagementSystem.Controller;


import com.example.LibraryManagementSystem.Entity.LibraryCard;
import com.example.LibraryManagementSystem.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController{

    @Autowired
    private CardService cardService;

    @PostMapping("/generatePlainCard")
    public ResponseEntity generatePlainCard(){

        LibraryCard libraryCard=cardService.generatePlainCard();

        String response="The new Card is generted and having a cardNo"+libraryCard.getCardNo();

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/associateWithStudent")
    public ResponseEntity associateWithStudent(@RequestParam("StudentId")Integer studnetId, @RequestParam("CardId")Integer cardNo){
        String res=cardService.associateWithStudent(studnetId,cardNo);
        return new ResponseEntity(res,HttpStatus.OK);
    }

}
