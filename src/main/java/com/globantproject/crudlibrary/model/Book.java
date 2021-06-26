package com.globantproject.crudlibrary.model;

import javax.persistence.*;
import java.util.Locale;

//This tells Hibernate to make a table out of this class
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(length = 4)
    private int editorialYear;

    @Column(length = 8, nullable = false)
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
        this.title = title.toUpperCase();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author.toUpperCase();
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
