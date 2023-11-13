package com.example.LibraryManagementSystem.Exception;

public class MaxBooksAlreadyIssuedException extends Exception{
    public MaxBooksAlreadyIssuedException(String message) {
        super(message);
    }
}
