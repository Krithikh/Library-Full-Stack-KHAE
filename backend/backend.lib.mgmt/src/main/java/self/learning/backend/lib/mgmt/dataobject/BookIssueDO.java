package self.learning.backend.lib.mgmt.dataobject;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_book_issue")
public class BookIssueDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_issue_id")
    private Long bookIssueId;
    @Column(name = "issue_number")
    private String issueNumber;
    @Column(name = "fk_membership")
    private Long membershipId;
    @Column(name = "fk_book_copy")
    private Long bookCopyId;
    @Column(name = "fk_reservation")
    private Long reservationId;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "status")
    private String status;

    public Long getBookIssueId() {
        return bookIssueId;
    }

    public void setBookIssueId(Long bookIssueId) {
        this.bookIssueId = bookIssueId;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public Long getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(Long bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
