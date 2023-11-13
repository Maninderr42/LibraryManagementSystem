package com.example.LibraryManagementSystem.Controller;


import com.example.LibraryManagementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tranaction")
public class TransactionController{

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/IssueBook/{bookId}/{cardId}")
    public ResponseEntity issueBook(@PathVariable("bookId") Integer bookId,@PathVariable("cardID")Integer cardId){
        try{
            String res=transactionService.issueBook(bookId,cardId);
            return new ResponseEntity(res, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/returnBook/{BookId}/{cardId}")
    public ResponseEntity returnBook(@PathVariable("bookId")Integer bookId,
                                     @PathVariable("cardId")Integer cardId){

        try {
            String res=transactionService.returnBook(bookId,cardId);
            return new ResponseEntity(res,HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

}
}
