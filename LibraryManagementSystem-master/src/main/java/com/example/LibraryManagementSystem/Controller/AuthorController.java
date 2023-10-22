package com.example.LibraryManagementSystem.Controller;


import com.example.LibraryManagementSystem.Entity.Author;
import com.example.LibraryManagementSystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController{

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    private ResponseEntity addAuthor(@RequestBody Author author){

        authorService.addAuthor(author);
        return new ResponseEntity<>("Add Author Successfully", HttpStatus.ACCEPTED);
    }


    @GetMapping("/getAuthorByTd/{id}")
    public ResponseEntity getAuthBy(@PathVariable("id") Integer id) throws Exception{
        try{
            Author author=authorService.getAuthorById(id);
            return new ResponseEntity<>(author, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllAuthorNames")
    private List<String> getAllAuthorName(){
        return authorService.getAllAuthorName();
    }

    @GetMapping("/getbookNameList")
    private ResponseEntity getBookNameList(@RequestParam("authorId") Integer authorId){
        List<String> BookName=authorService.getBookNameList(authorId);

        return new ResponseEntity<>(BookName, HttpStatus.OK);
    }
}
