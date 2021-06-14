package com.globantproject.crudlibrary;

import model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class CrudLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudLibraryApplication.class, args);

	}

	@GetMapping
	public List<Book> hello() {
		return List.of(
				new Book(
						1,
						"Title One",
						"Anonymous",
						LocalDate.of(2000, Month.JANUARY, 10),
						Boolean.TRUE
				)
		);
	}

}
