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


    public List<Book> getAllBooks() {
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

    public void createBook(Book book) throws BookBadRequestException {
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
                    "book with id " + bookId + " does not exist");
        }
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId,
                           Book updateBook) throws BookNotFoundException, BookBadRequestException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(
                        "book with id " + bookId + " does not exist"));

        if(updateBook.getAuthor() != null
                && updateBook.getAuthor().length() > 0
                && updateBook.getTitle() != null
                && updateBook.getTitle().length() > 0
                && updateBook.getEditorialYear() != 0
        ) {
            Optional<Book> bookByAuthorAndTitle = bookRepository
                    .findBookByAuthorAndTitle(updateBook.getAuthor(), updateBook.getTitle());

            book.setEditorialYear(updateBook.getEditorialYear());

            if(!bookByAuthorAndTitle.isPresent()) {
                book.setTitle(updateBook.getTitle());
                book.setAuthor(updateBook.getAuthor());

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

        book.setState(updateBook.getState());
        book.setReservation(updateBook.getReservation());

        bookRepository.save(book);
    }

    @Transactional
    public void updateReservation(Long bookId, Reservation updateReservation) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(
                        "book with id " + bookId + " does not exist"));

        book.getReservation().setStartDate(updateReservation.getStartDate());
        book.getReservation().setEndDate(updateReservation.getEndDate());

        bookRepository.save(book);
    }
}
