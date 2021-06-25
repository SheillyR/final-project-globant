package com.globantproject.crudlibrary.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String name;
    private String lastName;
    @Id
    private Integer documentNumber;
    private String email;

    public User() {

    }
    public User(String name, String lastName, Integer documentNumber, String email) {
        this.name = name;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase();
    }

    public Integer getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toUpperCase();
    }
}
