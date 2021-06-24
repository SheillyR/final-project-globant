package com.globantproject.crudlibrary.controller;

import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.Reservation;
import com.globantproject.crudlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/id/{bookId}")
    public Book getBookById(@PathVariable("bookId") Long bookId){
        return bookService.getBookById(bookId);
    }

    @PostMapping
    public void createNewBook(@RequestBody Book book){
        bookService.addNewBook(book);
    }

    @DeleteMapping(path = "{bookId}")
    public void deleteStudent(@PathVariable("bookId") Long bookId){
        bookService.deleteBook(bookId);
    }

    @PutMapping(path = "{bookId}")
    public void updateBook(
            @PathVariable("bookId") Long bookId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer editorialYear) {
        bookService.updateBook(bookId, title, author, editorialYear);
    }

    @PutMapping(path = "/updateReservation/{bookId}")
    public void updateReservation(
            @PathVariable("bookId") Long bookId,
            @RequestBody(required = false) Reservation reservation) {
        bookService.updateReservation(bookId, reservation);
    }

}
