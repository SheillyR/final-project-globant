package com.globantproject.crudlibrary.model;

import com.globantproject.crudlibrary.dao.BookDAO;

import java.util.ArrayList;

public class Book implements BookDAO {
    private int id;
    private String title;
    private String author;
    private int editorialDate;
    private Boolean state;
    private ReservationInfo reservationInfo;

    public Book() {
    }

    public Book(String title, String author, int editorialDate, Boolean state) {
        this.title = title;
        this.author = author;
        this.editorialDate = editorialDate;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEditorialDate() {
        return editorialDate;
    }

    public void setEditorialDate(int editorialDate) {
        this.editorialDate = editorialDate;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public ReservationInfo getReservationInfo() {
        return reservationInfo;
    }

    public void setReservationInfo(ReservationInfo reservationInfo) {
        this.reservationInfo = reservationInfo;
    }

    public static ArrayList<Book> makeBookList() {
        Book book = new Book();
        return book.read();
    }
}
