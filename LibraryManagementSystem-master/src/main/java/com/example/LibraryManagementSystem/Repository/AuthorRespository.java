package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRespository extends JpaRepository<Author, Integer> {
}
