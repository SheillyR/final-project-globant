package com.globantproject.crudlibrary.controller;

import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.Reservation;
import com.globantproject.crudlibrary.model.State;
import com.globantproject.crudlibrary.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    private List<Book> bookList;

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

    @BeforeEach
    void setUp() {
        this.bookList = new ArrayList<>();
        this.bookList.add(dummyBookOne);
        this.bookList.add(dummyBookTwo);
    }

    @Test
    public void itShouldFetchAllBooks() throws Exception {
        dummyBookOne.setReservation(dummyReservation);
        dummyBookTwo.setReservation(dummyReservation);

        given(bookService.getAllBooks()).willReturn(bookList);

        mvc.perform(get("/api/book/getAllBooks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void itShouldGetBooksByState() throws Exception {
        given(bookService.getBooksByState(State.AVAILABLE)).willReturn(bookList);

        mvc.perform((get("/api/book/getBooksByState/{state}", State.AVAILABLE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void itShouldFetchOneBookById() throws Exception {
        Long bookId = 1L;
        dummyBookOne.setReservation(dummyReservation);
        dummyBookOne.setId(bookId);

        given(bookService.getBookById(bookId)).willReturn(dummyBookOne);

        mvc.perform(get("/api/book/getBookById/{bookId}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(dummyBookOne.getTitle())))
                .andExpect(jsonPath("$.author", is(dummyBookOne.getAuthor())));
    }

    @Test
    void itShouldDeleteBook() throws Exception {
        Long bookId = 1L;
        dummyBookOne.setId(bookId);

        given(bookService.getBookById(bookId)).willReturn(dummyBookOne);
        doNothing().when(bookService).deleteBook(dummyBookOne.getId());

        mvc.perform(delete("/api/book/deleteBookById/{bookId}", dummyBookOne.getId()))
                .andExpect(status().isOk());
    }
}