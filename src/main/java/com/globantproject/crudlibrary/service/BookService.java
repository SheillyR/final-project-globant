package com.globantproject.crudlibrary.service;

import com.globantproject.crudlibrary.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.globantproject.crudlibrary.model.Book.makeBookList;

@Service
public class BookService {

    public List<Book> getBooks() {

        return makeBookList();
        /*
        return List.of(

                new Book(
                        "Title One",
                        "Anonymous",
                        2020,
                        Boolean.TRUE

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
}
