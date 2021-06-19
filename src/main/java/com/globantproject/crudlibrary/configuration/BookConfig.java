package com.globantproject.crudlibrary.configuration;

import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository) {
        return args -> {
            Book book1 = new Book(
                    "Title One",
                    "Anonymous",
                    2000,
                    Boolean.TRUE
            );
            Book book2 = new Book(
                    "Title Two",
                    "Anonymous",
                    1900,
                    Boolean.TRUE
            );

            repository.saveAll(
                    List.of(book1, book2)
            );
        };
    }
}
