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
                    "BEGINNING SPRING BOOT 2: APPLICATIONS AND MICROSERVICES WITH THE SPRING FRAMEWORK",
                    "K. SIVA PRASAD REDDY",
                    2017,
                    State.AVAILABLE
            );

            Reservation reservationTwo = new Reservation(
                    new Date(2019,04,10),
                    new Date(2021,05, 10)
            );
            Book bookTwo = new Book(
                    "CORE JAVA VOLUME I – FUNDAMENTALS",
                    "CAY S. HORSTMANN",
                    2019,
                    State.RESERVED
            );

            User userTwo = new User(
                    "JUANITA",
                    "LAZO",
                    56325639,
                    "JUANITALAZO@GMAIL.COM"
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
