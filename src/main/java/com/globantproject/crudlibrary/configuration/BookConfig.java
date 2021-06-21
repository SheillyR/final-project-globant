package com.globantproject.crudlibrary.configuration;

import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.ReservationInfo;
import com.globantproject.crudlibrary.model.User;
import com.globantproject.crudlibrary.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return args -> {
            Book book1 = new Book(
                    "Title One",
                    "Anonymous",
                    2000,
                    Book.State.AVAILABLE
            );

            ReservationInfo reservation1 = new ReservationInfo(
                    LocalDate.of(2020, Month.JANUARY, 10),
                    LocalDate.of(2021, Month.MAY, 10)
            );

            User user1 = new User(
                    "Pepito",
                    "Roca",
                    18256397,
                    "pepitoroca@gmail.com"
            );

            Book book2 = new Book(
                    "Title Two",
                    "Anonymous",
                    1900,
                    Book.State.AVAILABLE
            );
            ReservationInfo reservation2 = new ReservationInfo(
                    LocalDate.of(2019, Month.APRIL, 10),
                    LocalDate.of(2021, Month.MAY, 10)
            );

            User user2 = new User(
                    "Juanita",
                    "Lazo",
                    56325639,
                    "juanitalazo@gmail.com"
            );
            book1.setReservationInfo(reservation1);
            reservation1.setUser(user1);
            book2.setReservationInfo(reservation2);
            reservation2.setUser(user2);

            bookRepository.saveAll(
                    List.of(book1, book2)
            );

        };
    }
}
