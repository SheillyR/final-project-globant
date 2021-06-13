package model;

import java.util.Date;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private Date editorialDate;
    private Boolean state;

    public Book(Integer id, String title, String author, Date editorialDate, Boolean state) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.editorialDate = editorialDate;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getEditorialDate() {
        return editorialDate;
    }

    public Boolean getState() {
        return state;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEditorialDate(Date editorialDate) {
        this.editorialDate = editorialDate;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
