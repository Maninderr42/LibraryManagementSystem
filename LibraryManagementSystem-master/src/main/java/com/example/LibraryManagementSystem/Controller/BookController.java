package com.example.LibraryManagementSystem.Controller;


import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    private ResponseEntity addBook(@RequestBody Book book, @RequestParam("authorId") Integer authorId){
        try{
            String ans=bookService.addBook(book,authorId);
            return new ResponseEntity<>(ans, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getBookByGenre")
    public List<String> getBookByGenre(@RequestParam("genre") Genre genre){

            List<String> ans=bookService.getBookByGenre(genre);
            return ans;


    }

}
