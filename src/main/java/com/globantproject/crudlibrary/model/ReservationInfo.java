package com.globantproject.crudlibrary.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reservations")
public class ReservationInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // private User user;
    private Long reservationId;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public ReservationInfo(){

    }
    public ReservationInfo(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
