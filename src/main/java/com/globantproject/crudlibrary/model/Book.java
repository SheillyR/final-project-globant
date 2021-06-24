package com.globantproject.crudlibrary.model;

import javax.persistence.*;

//This tells Hibernate to make a table out of this class
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int editorialYear;
    @Enumerated(value = EnumType.STRING)
    private State state;
    @JoinColumn(name = "reservationId")
    @OneToOne(cascade = CascadeType.ALL)
    private Reservation reservation;

    public Book(){

    }

    public Book(String title, String author, int editorialYear, State state) {
        this.title = title;
        this.author = author;
        this.editorialYear= editorialYear;
        this.state = state;
        //this.reservationInfo = reservationInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getEditorialYear() {
        return editorialYear;
    }

    public void setEditorialYear(int editorialYear) {
        this.editorialYear = editorialYear;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
