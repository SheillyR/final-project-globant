package com.globantproject.crudlibrary.configuration;

import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.Reservation;
import com.globantproject.crudlibrary.model.State;
import com.globantproject.crudlibrary.model.User;
import com.globantproject.crudlibrary.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return args -> {
            Reservation nullReservation = null;

            Book bookOne = new Book(
                    "Title A",
                    "Anonymous",
                    2000,
                    State.AVAILABLE
            );

            Reservation reservationTwo = new Reservation(
                    new Date(2019,04,10),
                    new Date(2021,05, 10)
            );
            Book bookTwo = new Book(
                    "Title B",
                    "Anonymous",
                    1900,
                    State.RESERVED
            );

            User userTwo = new User(
                    "Juanita",
                    "Lazo",
                    56325639,
                    "juanitalazo@gmail.com"
            );

            bookOne.setReservation(nullReservation);
            bookTwo.setReservation(reservationTwo);
            reservationTwo.setUser(userTwo);

            bookRepository.saveAll(
                    List.of(bookOne, bookTwo)
            );
        };
    }
}
