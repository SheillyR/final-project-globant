package model;

public class User {
    private String name;
    private String lastName;
    private Integer documentNumber;
    private String email;

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
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        this.email = email;
    }
}
