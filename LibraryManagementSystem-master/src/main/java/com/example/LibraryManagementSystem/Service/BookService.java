package com.example.LibraryManagementSystem.Service;


import com.example.LibraryManagementSystem.Entity.Author;
import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.Exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.Repository.AuthorRespository;
import com.example.LibraryManagementSystem.Repository.BookRespository;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRespository bookRespository;

    @Autowired
    private AuthorRespository authorRespository;

    public String addBook(Book book, Integer authorId) throws Exception {

        Optional<Author> optionalAuthor=authorRespository.findById(authorId);

        if(!optionalAuthor.isPresent()){
            throw new AuthorNotFoundException("Author Id Entered id Invalid");
        }

        Author author=optionalAuthor.get();

        book.setAuthor(author);

        author.getBookList().add(book);

        authorRespository.save(author);

        return "Book has been added to the Db";



    }

    public List<String> getBookByGenre(Genre genre){

        List<Book> BookList= bookRespository.findBooksByGenre(genre);

        List<String> bookName=new ArrayList<>();
        for(Book book :BookList){
            bookName.add(book.getBookName());
        }
        return bookName;

    }
}
