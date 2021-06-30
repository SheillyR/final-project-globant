package com.globantproject.crudlibrary.service;

import com.globantproject.crudlibrary.exception.BookBadRequestException;
import com.globantproject.crudlibrary.exception.BookNotFoundException;
import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.Reservation;
import com.globantproject.crudlibrary.model.State;
import com.globantproject.crudlibrary.model.User;
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

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock private BookRepository bookRepository;
    private BookService underTest;

    @BeforeEach
    void setUp() {
        underTest = new BookService(bookRepository);
    }

    Book bookDummyOne = new Book(
            "Title One",
            "Anonymous",
            2000,
            State.AVAILABLE
    );
    Book bookDummyTwo = new Book(
            "Title Two",
            "Anonymous 2",
            2020,
            State.AVAILABLE
    );

    Reservation reservationDummy = null;
    Reservation reservationDummyTwo = new Reservation(
            new Date(2019,04,10),
            new Date(2021,05, 10)
    );

    User userDummyTwo = new User(
            "Juanita",
            "Lazo",
            56325639,
            "juanitalazo@gmail.com"
    );
/*
    @Test
    void canGetAllBooks() {
        // when
        underTest.getBooks();
        // then
        verify(bookRepository).findAll(Sort.by("title").ascending());
    }
*/

    @Test
    void canGetAllBooks() {
        // given
        bookDummyOne.setReservation(reservationDummy);
        bookDummyTwo.setReservation(reservationDummy);

        List<Book> booksDummy = new ArrayList<>(Arrays.asList(bookDummyOne, bookDummyTwo));
        // when
        when((bookRepository).findAll(Sort.by("title").ascending())).thenReturn(booksDummy);
        List<Book> books = underTest.getBooks();
        assertThat(books).isEqualTo(booksDummy);
        // then
        verify(bookRepository).findAll(Sort.by("title").ascending());
    }

    @Test
    void canGetBooksByState() {
        // given
        bookDummyOne.setReservation(reservationDummy);
        bookDummyTwo.setReservation(reservationDummy);

        List<Book> booksDummy = new ArrayList<>(
                Arrays.asList(
                        bookDummyTwo,
                        bookDummyOne
                )
        );

        // when
        when((bookRepository)
                .findBooksByState(State.AVAILABLE, Sort.by("title").ascending()))
                .thenReturn(booksDummy);

        List<Book> books = underTest.getBooksByState(State.AVAILABLE);
        assertThat(books).isEqualTo(booksDummy);
        // then
        verify(bookRepository).findBooksByState(State.AVAILABLE, Sort.by("title").ascending());

    }

    @Test
    void canGetBookById() throws BookNotFoundException{
        // given
        bookDummyTwo.setReservation(reservationDummy);
        bookDummyTwo.setId(2L);
        // when
        when(bookRepository.findById(bookDummyTwo.getId())).thenReturn(Optional.of(bookDummyTwo));
        Book book = underTest.getBookById(2L);
        assertThat(book.getId()).isEqualTo(2L);
        // then
        verify(bookRepository).findById(bookDummyTwo.getId());
    }

    @Test
    void willThrowWhenBookIdNotFound() throws BookNotFoundException{
        // given
        Long id = 1L;

        // when
        // then
        assertThatThrownBy(() -> underTest.getBookById(id))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessageContaining("book with id 1 does not exist");

    }

    @Test
    void canAddBook() throws BookBadRequestException {
        // given
        bookDummyOne.setReservation(reservationDummy);

        // when
        underTest.addNewBook(bookDummyOne);

        // then
        ArgumentCaptor<Book> bookArgumentCaptor =
                ArgumentCaptor.forClass(Book.class);

        verify(bookRepository).
                save(bookArgumentCaptor.capture());

        Book capturedBook = bookArgumentCaptor.getValue();

        assertThat(capturedBook).isEqualTo(bookDummyOne);
    }

    @Test
    void willThrowWhenTitleAndAuthorAreTaken() {
        // given
        bookDummyOne.setReservation(reservationDummy);

        given(bookRepository.findBookByAuthorAndTitle(anyString(), anyString()))
                .willReturn(java.util.Optional.of(bookDummyOne));

        // when
        // then
        assertThatThrownBy(() -> underTest.addNewBook(bookDummyOne))
                .isInstanceOf(BookBadRequestException.class)
                .hasMessageContaining("This author and title are taken, enter other values");

        verify(bookRepository, never()).save(any());
    }

    @Test
    void willThrowWhenBookIsReservedAndItIsNotFillInfo() {
        // given
        bookDummyOne.setReservation(reservationDummy);
        bookDummyOne.setState(State.RESERVED);

        // when
        // then
        assertThatThrownBy(() -> underTest.addNewBook(bookDummyOne))
                .isInstanceOf(BookBadRequestException.class)
                .hasMessageContaining("Complete reservation info");

        verify(bookRepository, never()).save(any());
    }

    @Test
    void willThrowWhenBookIsAvailableAndItIsFillInfo() {
        // given
        bookDummyTwo.setReservation(reservationDummyTwo);
        reservationDummyTwo.setUser(userDummyTwo);

        // when
        // then
        assertThatThrownBy(() -> underTest.addNewBook(bookDummyTwo))
                .isInstanceOf(BookBadRequestException.class)
                .hasMessageContaining("Reservation info must be null");

        verify(bookRepository, never()).save(any());
    }

    @Test
    void canDeleteBook() throws BookNotFoundException {
        //given
        given(bookRepository.existsById(anyLong())).willReturn(true);

        //when
        underTest.deleteBook(2L);

        // then
        ArgumentCaptor<Long> bookArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);

        verify(bookRepository).
                existsById(bookArgumentCaptor.capture());
        assertThat(bookArgumentCaptor.getValue()).isEqualTo(2L);

        verify(bookRepository, times(1)).deleteById(bookArgumentCaptor.capture());
        assertThat(bookArgumentCaptor.getValue()).isEqualTo(2L);
    }

    @Test
    void willThrowWhenBookDeletedNotFound() throws BookNotFoundException{
        // given
        given(bookRepository.existsById(anyLong())).willReturn(false);
        Long id = 1L;

        // when
        // then
        assertThatThrownBy(() -> underTest.deleteBook(id))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessageContaining("book with id 1 does not exist");

    }

    @Test
    void updateBook() throws BookBadRequestException, BookNotFoundException {
        // given
        bookDummyOne.setReservation(reservationDummy);
        bookDummyOne.setId(1L);

        Book newBook = new Book(
                "Title One",
                "Anonymous",
                2021,
                State.AVAILABLE
        );
        newBook.setReservation(reservationDummy);
        newBook.setId(1L);

        // when
        when(bookRepository.findById(bookDummyOne.getId())).thenReturn(Optional.of(newBook));
        underTest.updateBook(bookDummyOne.getId(), newBook);

        // then
        ArgumentCaptor<Book> bookArgumentCaptor =
                ArgumentCaptor.forClass(Book.class);

        verify(bookRepository).save(bookArgumentCaptor.capture());

        Book capturedBook = bookArgumentCaptor.getValue();

        assertThat(capturedBook).isEqualTo(newBook);

    }
    /*
    @Test
    void willThrowWhenTitleAndAuthorAreTakenInUpdate() {
        // given
        bookDummyOne.setReservation(reservationDummy);
        bookDummyOne.setId(1L);

        when(bookRepository.findById(bookDummyOne.getId())).thenReturn(Optional.of(bookDummyOne));
        given(bookRepository.findBookByAuthorAndTitle("Anonymous", "Title One"))
                .willReturn(java.util.Optional.of(bookDummyOne));

        // when
        // then
        assertThatThrownBy(() -> underTest.updateBook(1L, bookDummyOne))
                .isInstanceOf(BookBadRequestException.class)
                .hasMessageContaining("This author and title are taken, enter other values");

        verify(bookRepository, never()).save(any());
    }
*/
    @Test
    @Disabled
    void updateReservation() {
    }
}