package com.globantproject.crudlibrary.model;

import javax.persistence.*;

//This tells Hibernate to make a table out of this class
@Entity
@Table
public class Book {
    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)

    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )

    private Long id;
    private String title;
    private String author;
    private int editorialYear;
    private Boolean state;
    // private ReservationInfo reservationInfo;

    public Book(){

    }
    public Book(String title, String author, int editorialYear, Boolean state) {
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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

}
