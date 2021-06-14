package com.globantproject.crudlibrary.service;

import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.ReservationInfo;
import com.globantproject.crudlibrary.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class BookService {

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
