package com.globantproject.crudlibrary.service;

import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        return bookRepository.findAll();

        /*
        return List.of(
                new Book(
                        1,
                        "Title One",
                        "Anonymous",
                        2000,
                        Boolean.TRUE,
                        new ReservationInfo(
                                new User(
                                        "Maria",
                                        "Lipa",
                                        73589621,
                                        "maria@gmail.com"
                                ),
                                LocalDate.of(2000, Month.JANUARY, 10),
                                LocalDate.of(2000, Month.JANUARY, 10)
                        )
                )
        );

         */
    }


    public void addNewBook(Book book) {
        Optional<Book> bookByAuthor = bookRepository
                .findStudentByAuthor(book.getAuthor());
        if(bookByAuthor.isPresent()){
            throw new IllegalStateException("author taken");
        }
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        boolean existsBook = bookRepository.existsById(bookId);
        if (!existsBook) {
            throw new IllegalStateException(
                    "book with id " + bookId + " does not exists");
        }
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateStudent(Long bookId,
                              String title,
                              String author) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException(
                        "book with id " + bookId + " does not exists"));

        if(title != null &&
                title.length() > 0 &&
                !Objects.equals(book.getTitle(), title)) {
            book.setTitle(title);
        }

        if(author != null &&
                author.length() > 0 &&
                !Objects.equals(book.getAuthor(), author)) {
            Optional<Book> bookByAuthor = bookRepository
                    .findStudentByAuthor(author);
            if(bookByAuthor.isPresent()) {
                book.setAuthor(author);
            } else {
                throw new IllegalStateException("author taken");
            }
        }
    }
}
