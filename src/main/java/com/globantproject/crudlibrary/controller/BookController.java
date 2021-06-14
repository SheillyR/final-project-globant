package com.globantproject.crudlibrary.controller;

import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.ReservationInfo;
import com.globantproject.crudlibrary.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "/api/book")
public class BookController {

    @GetMapping
    public List<Book> getBooks() {
        return List.of(
                new Book(
                        1,
                        "Title One",
                        "Anonymous",
                        LocalDate.of(2000, Month.JANUARY, 10),
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
    }

}
