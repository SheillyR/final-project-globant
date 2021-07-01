package com.globantproject.crudlibrary.service;

import com.globantproject.crudlibrary.exception.BookBadRequestException;
import com.globantproject.crudlibrary.exception.BookNotFoundException;
import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.Reservation;
import com.globantproject.crudlibrary.model.State;
import com.globantproject.crudlibrary.model.User;
import com.globantproject.crudlibrary.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
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

    Book dummyBookOne = new Book(
            "Title One",
            "Anonymous",
            2000,
            State.AVAILABLE
    );
    Book dummyBookTwo = new Book(
            "Title Two",
            "Anonymous 2",
            2020,
            State.AVAILABLE
    );
    Reservation dummyReservation = null;
    Reservation dummyReservationTwo = new Reservation(
            new Date(2019,04,10),
            new Date(2021,05, 10)
    );
    User dummyUserTwo = new User(
            "Juanita",
            "Lazo",
            56325639,
            "juanitalazo@gmail.com"
    );

    @Test
    void itShouldGetAllBooks() {
        // given
        dummyBookOne.setReservation(dummyReservation);
        dummyBookTwo.setReservation(dummyReservation);
        List<Book> booksDummy = new ArrayList<>(Arrays.asList(dummyBookOne, dummyBookTwo));

        // when
        when((bookRepository).findAll(Sort.by("title").ascending())).thenReturn(booksDummy);
        List<Book> books = underTest.getAllBooks();
        assertThat(books).isEqualTo(booksDummy);

        // then
        verify(bookRepository).findAll(Sort.by("title").ascending());
    }

    @Test
    void itShouldGetBooksByState() {
        // given
        dummyBookOne.setReservation(dummyReservation);
        dummyBookTwo.setReservation(dummyReservation);

        List<Book> dummyBooks = Arrays.asList(
                        dummyBookTwo,
                        dummyBookOne
        );

        // when
        when((bookRepository)
                .findBooksByState(State.AVAILABLE, Sort.by("title").ascending()))
                .thenReturn(dummyBooks);

        List<Book> books = underTest.getBooksByState(State.AVAILABLE);
        assertThat(books).isEqualTo(dummyBooks);

        // then
        verify(bookRepository).findBooksByState(State.AVAILABLE, Sort.by("title").ascending());
    }

    @Test
    void itShouldGetBookById() throws BookNotFoundException{
        // given
        dummyBookTwo.setReservation(dummyReservation);
        dummyBookTwo.setId(2L);

        // when
        when(bookRepository.findById(dummyBookTwo.getId())).thenReturn(Optional.of(dummyBookTwo));
        Book book = underTest.getBookById(2L);
        assertThat(book.getId()).isEqualTo(2L);

        // then
        verify(bookRepository).findById(dummyBookTwo.getId());
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
    void itShouldAddBook() throws BookBadRequestException {
        // given
        dummyBookOne.setReservation(dummyReservation);

        // when
        underTest.createBook(dummyBookOne);

        // then
        ArgumentCaptor<Book> bookArgumentCaptor =
                ArgumentCaptor.forClass(Book.class);

        verify(bookRepository).
                save(bookArgumentCaptor.capture());

        Book capturedBook = bookArgumentCaptor.getValue();

        assertThat(capturedBook).isEqualTo(dummyBookOne);
    }

    @Test
    void willThrowWhenTitleAndAuthorAreTaken() {
        // given
        dummyBookOne.setReservation(dummyReservation);

        given(bookRepository.findBookByAuthorAndTitle(anyString(), anyString()))
                .willReturn(java.util.Optional.of(dummyBookOne));

        // when
        // then
        assertThatThrownBy(() -> underTest.createBook(dummyBookOne))
                .isInstanceOf(BookBadRequestException.class)
                .hasMessageContaining("This author and title are taken, enter other values");

        verify(bookRepository, never()).save(any());
    }

    @Test
    void willThrowWhenBookIsReservedAndInfoIsNotFill() {
        // given
        dummyBookOne.setReservation(dummyReservation);
        dummyBookOne.setState(State.RESERVED);

        // when
        // then
        assertThatThrownBy(() -> underTest.createBook(dummyBookOne))
                .isInstanceOf(BookBadRequestException.class)
                .hasMessageContaining("Complete reservation info");

        verify(bookRepository, never()).save(any());
    }

    @Test
    void willThrowWhenBookIsAvailableAndInfoIsFill() {
        // given
        dummyBookTwo.setReservation(dummyReservationTwo);
        dummyReservationTwo.setUser(dummyUserTwo);

        // when
        // then
        assertThatThrownBy(() -> underTest.createBook(dummyBookTwo))
                .isInstanceOf(BookBadRequestException.class)
                .hasMessageContaining("Reservation info must be null");

        verify(bookRepository, never()).save(any());
    }

    @Test
    void itShouldDeleteBookById() throws BookNotFoundException {
        // given
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
    void willThrowWhenDeleteBookNotFound() throws BookNotFoundException{
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
    void itShouldUpdateBook() throws BookBadRequestException, BookNotFoundException {
        // given
        dummyBookOne.setReservation(dummyReservation);
        dummyBookOne.setId(1L);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(dummyBookOne));

        Book bookUpdate = new Book(
                "Title Update",
                "None",
                2020,
                State.AVAILABLE
        );

        bookUpdate.setReservation(dummyReservation);
        bookUpdate.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookUpdate));
        underTest.updateBook(bookUpdate.getId(), bookUpdate);

        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        assertThat(bookArgumentCaptor.getValue().getTitle()).isEqualTo("TITLE UPDATE");
    }

    @Test
    void willThrowWhenTitleAndAuthorAreTakenInUpdate() {
        // given
        dummyBookOne.setReservation(dummyReservation);
        dummyBookOne.setId(1L);

        when(bookRepository.findById(dummyBookOne.getId())).thenReturn(Optional.of(dummyBookOne));
        given(bookRepository.findBookByAuthorAndTitle("Anonymous", "Title One"))
                .willReturn(java.util.Optional.of(dummyBookOne));

        // when
        // then
        assertThatThrownBy(() -> underTest.updateBook(1L, dummyBookOne))
                .isInstanceOf(BookBadRequestException.class)
                .hasMessageContaining("author and title are taken");

        verify(bookRepository, never()).save(any());
    }

    @Test
    void willThrowWhenBookIsReservedAndInfoIsNotFillInUpdate(){
        // given
        dummyBookOne.setReservation(dummyReservation);
        dummyBookOne.setId(1L);
        dummyBookOne.setState(State.RESERVED);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(dummyBookOne));

        // when
        // then
        assertThatThrownBy(() -> underTest.updateBook(dummyBookOne.getId(), dummyBookOne))
                .isInstanceOf(BookBadRequestException.class)
                .hasMessageContaining("Complete reservation info");

        verify(bookRepository, never()).save(any());
    }

    @Test
    void willThrowWhenBookIsAvailableAndInfoIsFillInUpdate() {
        // given
        dummyBookTwo.setReservation(dummyReservationTwo);
        dummyReservationTwo.setUser(dummyUserTwo);
        dummyBookTwo.setId(2L);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(dummyBookTwo));

        // when
        // then
        assertThatThrownBy(() -> underTest.updateBook(dummyBookTwo.getId(), dummyBookTwo))
                .isInstanceOf(BookBadRequestException.class)
                .hasMessageContaining("Reservation info must be null");

        verify(bookRepository, never()).save(any());
    }

    @Test
    void itShouldUpdateReservation() throws BookNotFoundException {
        // given
        dummyBookTwo.setState(State.RESERVED);
        dummyBookTwo.setReservation(dummyReservationTwo);
        dummyBookTwo.setId(1L);

        // when
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(dummyBookTwo));
        Reservation updateReservation = new Reservation(
                new Date(2019, 04, 10),
                new Date(2021, 06, 30)
        );

        dummyBookTwo.setReservation(updateReservation);
        dummyBookTwo.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(dummyBookTwo));
        underTest.updateReservation(dummyBookTwo.getId(), updateReservation);

        // then
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        Book capturedBook = bookArgumentCaptor.getValue();

        assertThat(capturedBook.getReservation().getEndDate()).isEqualTo(new Date(2021, 06, 30));
    }
}