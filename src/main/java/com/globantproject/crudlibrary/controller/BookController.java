package com.globantproject.crudlibrary.controller;

import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/book")
public class BookController {

    private final BookService bookService;

    // Instance of BookService class in controller
    // Add annotation @Service in BookService class to work
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

}
