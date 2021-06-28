package com.globantproject.crudlibrary.service;

import com.globantproject.crudlibrary.exception.BookBadRequestException;
import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.Reservation;
import com.globantproject.crudlibrary.model.State;
import com.globantproject.crudlibrary.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock private BookRepository bookRepository;private BookService underTest;

    @BeforeEach
    void setUp() {
        underTest = new BookService(bookRepository);
    }

    @Test
    void canGetAllBooks() {
        // when
        underTest.getBooks();
        // then
        verify(bookRepository).findAll(Sort.by("title").ascending());
    }

    @Test
    @Disabled
    void getBooksByState() {
    }

    @Test
    @Disabled
    void getBookById() {
    }

    @Test
    void canAddBook() throws BookBadRequestException {
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

        // when
        underTest.addNewBook(book);

        // then
        ArgumentCaptor<Book> bookArgumentCaptor =
                ArgumentCaptor.forClass(Book.class);

        verify(bookRepository).
                save(bookArgumentCaptor.capture());

        Book capturedBook = bookArgumentCaptor.getValue();

        assertThat(capturedBook).isEqualTo(book);
    }

    @Test
    void willThrowWhenTitleAndAuthorAreTaken() throws BookBadRequestException {
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

        given(bookRepository.findBookByAuthorAndTitle(anyString(), anyString()))
                .willReturn(java.util.Optional.of(book));

        // when
        // then
        assertThatThrownBy(() -> underTest.addNewBook(book))
                .isInstanceOf(BookBadRequestException.class)
                .hasMessageContaining("This author and title are taken, enter other values");

        verify(bookRepository, never()).save(any());
    }

    @Test
    @Disabled
    void deleteBook() {
    }

    @Test
    @Disabled
    void updateBook() {
    }

    @Test
    @Disabled
    void updateReservation() {
    }
}