package model;

import java.util.Date;

public class Book {
    private int id;
    private String title;
    private String author;
    private Date editorialDate;
    private Boolean state;
    private User user;

    public Book(Integer id, String title, String author, Date editorialDate, Boolean state) {
        this.id = id;
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

    public Date getEditorialDate() {
        return editorialDate;
    }

    public void setEditorialDate(Date editorialDate) {
        this.editorialDate = editorialDate;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
