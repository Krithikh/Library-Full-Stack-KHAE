package self.learning.backend.lib.mgmt.dataobject;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_book_return")
public class BookReturnDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_return_id")
    private Long bookReturnId;
    @Column(name = "return_number")
    private String returnNumber;
    @Column(name = "fk_book_issue")
    private Long bookIssueId;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @Column(name = "status")
    private String status;

    public Long getBookReturnId() {
        return bookReturnId;
    }

    public void setBookReturnId(Long bookReturnId) {
        this.bookReturnId = bookReturnId;
    }

    public String getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(String returnNumber) {
        this.returnNumber = returnNumber;
    }

    public Long getBookIssueId() {
        return bookIssueId;
    }

    public void setBookIssueId(Long bookIssueId) {
        this.bookIssueId = bookIssueId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
