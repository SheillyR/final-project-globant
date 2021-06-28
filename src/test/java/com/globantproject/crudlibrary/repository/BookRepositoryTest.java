package com.globantproject.crudlibrary.repository;

import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.Reservation;
import com.globantproject.crudlibrary.model.State;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository underTest;

    @Test
    void itShouldFindBookByAuthorAndTitle() {
        // given
        String title = "Title One";
        String author = "Anonymous";
        Reservation reservationInfoTest = null;

        Book book = new Book(
                title,
                author,
                2000,
                State.AVAILABLE
        );
        book.setReservation(reservationInfoTest);
        underTest.save(book);


        // when
        Optional<Book> expected = underTest.findBookByAuthorAndTitle(title, author);

        // then
        assertThat(book).usingRecursiveComparison().isEqualTo(expected);
    }

    @Disabled
    @Test
    void findBooksByState() {
    }
}