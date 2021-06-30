package com.globantproject.crudlibrary.configuration;

import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.Reservation;
import com.globantproject.crudlibrary.model.State;
import com.globantproject.crudlibrary.model.User;
import com.globantproject.crudlibrary.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return args -> {
            Reservation reservationInfoTest = null;

            Book book1 = new Book(
                    "Title A",
                    "Anonymous",
                    2000,
                    State.AVAILABLE
            );

            Reservation reservation2 = new Reservation(
                    new Date(2019,04,10),
                    new Date(2021,05, 10)
            );
            Book book2 = new Book(
                    "Title B",
                    "Anonymous",
                    1900,
                    State.RESERVED
            );

            User user2 = new User(
                    "Juanita",
                    "Lazo",
                    56325639,
                    "juanitalazo@gmail.com"
            );

            book1.setReservation(reservationInfoTest);
            book2.setReservation(reservation2);
            reservation2.setUser(user2);

            bookRepository.saveAll(
                    List.of(book1, book2)
            );

        };
    }
}
