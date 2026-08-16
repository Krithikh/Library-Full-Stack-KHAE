package self.learning.backend.lib.mgmt.dto.response;

import java.time.LocalDate;

public class ReservationResponseDto {
    private Long reservationId;
    private String reservationNumber;
    private Long membershipId;
    private Long bookId;
    private LocalDate reservedDate;
    private String status;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
