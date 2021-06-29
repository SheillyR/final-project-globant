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
    public Book updateBook(Long bookId,
                           Book newBook) throws BookNotFoundException, BookBadRequestException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(
                        "book with id " + bookId + " does not exist"));

        if((newBook.getAuthor() != null
                && newBook.getAuthor().length() > 0
                && !Objects.equals(book.getAuthor(), newBook.getAuthor()))
                && newBook.getTitle() != null
                && newBook.getTitle().length() > 0
                && !Objects.equals(book.getTitle(), newBook.getTitle())
                && newBook.getEditorialYear() != 0
        ) {
            Optional<Book> bookByAuthorAndTitle = bookRepository
                    .findBookByAuthorAndTitle(newBook.getAuthor(), newBook.getTitle());

            book.setEditorialYear(newBook.getEditorialYear());

            if(!bookByAuthorAndTitle.isPresent()) {
                book.setTitle(newBook.getTitle());
                book.setAuthor(newBook.getAuthor());

            } else {
                throw new BookBadRequestException("author and title are taken");
            }
        }

        if(Objects.equals(book.getState(), State.RESERVED) && book.getReservation() == null){
            throw new BookBadRequestException("Complete reservation info");
        }
        if(Objects.equals(book.getState(), State.AVAILABLE) && book.getReservation() != null){
            throw new BookBadRequestException("Reservation info must be null");
        }

        book.setState(newBook.getState());
        book.setReservation(newBook.getReservation());

        return bookRepository.save(book);
    }

    @Transactional
    public Book updateReservation(Long bookId, Reservation newReservation) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(
                        "book with id " + bookId + " does not exist"));

        book.getReservation().setStartDate(newReservation.getStartDate());
        book.getReservation().setEndDate(newReservation.getEndDate());

        return bookRepository.save(book);
    }

}
