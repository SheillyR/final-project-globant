package com.globantproject.crudlibrary.service;

import com.globantproject.crudlibrary.exception.BookBadRequestException;
import com.globantproject.crudlibrary.exception.BookNotFoundException;
import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.Reservation;
import com.globantproject.crudlibrary.model.State;
import com.globantproject.crudlibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getBooks() {

        return bookRepository.findAll(Sort.by("title").ascending());
    }

    public List<Book> getBooksByState(State state) {
        return bookRepository.findBooksByState(state, Sort.by("title").ascending());
    }

    public Book getBookById(Long bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(
                        "book with id " + bookId + " does not exist"));
        return book;
    }

    public void addNewBook(Book book) throws BookBadRequestException {
        Optional<Book> bookByAuthorAndTitle = bookRepository
                .findBookByAuthorAndTitle(book.getAuthor(), book.getTitle());
        if(bookByAuthorAndTitle.isPresent()){
            throw new BookBadRequestException("This author and title are taken, enter other values");
        }
        if(Objects.equals(book.getState(), State.RESERVED) && book.getReservation() == null){
            throw new BookBadRequestException("Complete reservation info");
        }
        if(Objects.equals(book.getState(), State.AVAILABLE) && book.getReservation() != null){
            throw new BookBadRequestException("Reservation info must be null");
        }
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) throws BookNotFoundException {
        boolean existsBook = bookRepository.existsById(bookId);
        if (!existsBook) {
            throw new BookNotFoundException(
                    "book with id " + bookId + " does not exists");
        }
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId,
                              String title,
                              String author,
                              Integer editorialYear) throws BookNotFoundException, BookBadRequestException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(
                        "book with id " + bookId + " does not exist"));

        if(title != null &&
                title.length() > 0 &&
                !Objects.equals(book.getTitle(), title)) {
            book.setTitle(title);
        }

        if(author != null &&
                author.length() > 0 &&
                !Objects.equals(book.getAuthor(), author)) {
            Optional<Book> bookByAuthor = bookRepository
                    .findBookByAuthorAndTitle(author, title);
            if(bookByAuthor.isPresent()) {
                book.setAuthor(author);
            } else {
                throw new BookBadRequestException("author taken");
            }
        }
    }

    @Transactional
    public void updateReservation(Long bookId, Reservation reservation) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(
                        "book with id " + bookId + " does not exist"));
        book.setReservation(reservation);
    }

}
