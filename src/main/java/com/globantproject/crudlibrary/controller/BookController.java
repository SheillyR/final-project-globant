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

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/getAllBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/getBooksByState/{state}")
    public List<Book> getBooksByState(@PathVariable("state") State state) {
        return bookService.getBooksByState(state);
    }

    @GetMapping(path = "/getBookById/{bookId}")
    public Book getBookById(@PathVariable("bookId") Long bookId)
            throws BookNotFoundException {
        return bookService.getBookById(bookId);
    }

    @PostMapping(path = "/createBook")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createBook(@RequestBody Book book)
            throws BookBadRequestException {
        bookService.createBook(book);
    }

    @DeleteMapping(path = "/deleteBookById/{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId)
            throws BookNotFoundException {
        bookService.deleteBook(bookId);
    }

    @PutMapping(path = "/updateBook/{bookId}")
    public void updateBook(
            @PathVariable("bookId") Long bookId,
            @RequestBody(required = false) Book book)
            throws BookNotFoundException, BookBadRequestException {
        bookService.updateBook(bookId, book);
    }

    @PutMapping(path = "/updateReservationByBookId/{bookId}")
    public void updateReservationByBookId(
            @PathVariable("bookId") Long bookId,
            @RequestBody(required = false) Reservation reservation)
            throws BookNotFoundException {
        bookService.updateReservation(bookId, reservation);
    }
}
