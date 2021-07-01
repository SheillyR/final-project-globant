package com.globantproject.crudlibrary.repository;

import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.Reservation;
import com.globantproject.crudlibrary.model.State;
import com.globantproject.crudlibrary.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository underTest;

    Reservation nullReservation = null;

    Book bookOne = new Book(
            "Title One",
            "Anonymous",
            2000,
            State.AVAILABLE
    );

    Book bookTwo = new Book(
            "Hola Mundo",
            "None",
            2021,
            State.RESERVED
    );

    Reservation reservationTwo = new Reservation(
            new Date(2019,04,10),
            new Date(2021,05, 10)
    );

    User userTwo = new User(
            "Juanita",
            "Lazo",
            56325639,
            "juanitalazo@gmail.com"
    );

    List<Book> availableBooks = new ArrayList<>();
    List<Book> reservedBooks = new ArrayList<>();

    @Test
    void itShouldFindBookByAuthorAndTitle() {
        // given
        bookOne.setReservation(nullReservation);
        underTest.save(bookOne);

        // when
        Optional<Book> expected = underTest.findBookByAuthorAndTitle("Anonymous", "Title One");

        // then
        assertThat(bookOne).isEqualTo(expected.get());
    }

    @Test
    void findBooksByAvailableState() {
        // given
        bookOne.setReservation(nullReservation);
        reservationTwo.setUser(userTwo);
        bookTwo.setReservation(reservationTwo);

        underTest.save(bookOne);
        underTest.save(bookTwo);

        availableBooks.add(bookOne);

        // when
        List<Book> expected = underTest.findBooksByState(State.AVAILABLE, Sort.by("title").ascending());

        // then
        assertThat(availableBooks).isEqualTo(expected);
    }

    @Test
    void findBooksByReservedState() {
        // given
        bookOne.setReservation(nullReservation);
        reservationTwo.setUser(userTwo);
        bookTwo.setReservation(reservationTwo);

        underTest.save(bookOne);
        underTest.save(bookTwo);

        reservedBooks.add(bookTwo);

        // when
        List<Book> expected = underTest.findBooksByState(State.RESERVED, Sort.by("title").ascending());

        // then
        assertThat(reservedBooks).isEqualTo(expected);
    }
}