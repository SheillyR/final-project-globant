package com.globantproject.crudlibrary.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long reservationId;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    public Reservation(){

    }
    public Reservation(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
