package com.globantproject.crudlibrary.controller;

import com.globantproject.crudlibrary.exception.BookBadRequestException;
import com.globantproject.crudlibrary.exception.BookNotFoundException;
import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.Reservation;
import com.globantproject.crudlibrary.model.State;
import com.globantproject.crudlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping(path = "/state/{state}")
    public List<Book> getBooksByState(@PathVariable("state") State state) {

        return bookService.getBooksByState(state);
    }

    @GetMapping(path = "/id/{bookId}")
    public Book getBookById(@PathVariable("bookId") Long bookId) throws BookNotFoundException {
        return bookService.getBookById(bookId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createNewBook(@RequestBody Book book) throws BookBadRequestException {
        bookService.addNewBook(book);
    }

    @DeleteMapping(path = "{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId) throws BookNotFoundException {
        bookService.deleteBook(bookId);
    }

    @PutMapping(path = "/updateBook/{bookId}")
    public void updateBook(
            @PathVariable("bookId") Long bookId,
            @RequestBody(required = false) Book book) throws BookNotFoundException, BookBadRequestException {
        bookService.updateBook(bookId, book);
    }

    @PutMapping(path = "/updateReservation/{bookId}")
    public void updateReservation(
            @PathVariable("bookId") Long bookId,
            @RequestBody(required = false) Reservation reservation) throws BookNotFoundException {
        bookService.updateReservation(bookId, reservation);
    }

}
