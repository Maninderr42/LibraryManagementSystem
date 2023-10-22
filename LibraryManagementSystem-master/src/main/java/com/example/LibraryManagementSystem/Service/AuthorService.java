package com.example.LibraryManagementSystem.Service;


import com.example.LibraryManagementSystem.Entity.Author;
import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Repository.AuthorRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRespository authorRespository;
    public void addAuthor(Author author){
        authorRespository.save(author);

    }

    public Author getAuthorById(Integer id) throws Exception{
        Optional<Author> optionalAuthor=authorRespository.findById(id);
        if(!optionalAuthor.isPresent()){
            throw  new Exception("The Id Entered is invalid");
        }
        Author author=optionalAuthor.get();

        return author;
    }

    public List<String> getAllAuthorName(){
        List<Author> authors=authorRespository.findAll();
        List<String> li=new ArrayList<>();
        for(Author author:authors){
            li.add(author.getAuthorName());
        }
        return li;
    }

    public List<String> getBookNameList(Integer authorId){

        List<String> li=new ArrayList<>();

        Author author=authorRespository.findById(authorId).get();
        List<Book> bookList=author.getBookList();

        for(Book book:bookList){
            li.add(book.getBookName());
        }
        return li;

    }
}
