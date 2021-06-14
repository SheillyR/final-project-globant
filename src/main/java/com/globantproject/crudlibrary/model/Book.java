package model;

import java.time.LocalDate;
import java.util.Date;

public class Book {
    private int id;
    private String title;
    private String author;
    private LocalDate editorialDate;
    private Boolean state;
    private ReservationInfo reservationInfo;

    public Book(int id, String title, String author, LocalDate editorialDate, Boolean state, ReservationInfo reservationInfo) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.editorialDate = editorialDate;
        this.state = state;
        this.reservationInfo = reservationInfo;
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

    public LocalDate getEditorialDate() {
        return editorialDate;
    }

    public void setEditorialDate(LocalDate editorialDate) {
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
}
