package model;

import java.time.LocalDate;

public class ReservationInfo {
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;

    public ReservationInfo(User user, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
