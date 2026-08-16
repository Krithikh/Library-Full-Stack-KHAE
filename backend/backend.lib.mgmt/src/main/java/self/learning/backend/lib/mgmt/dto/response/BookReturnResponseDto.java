package self.learning.backend.lib.mgmt.dto.response;

import java.time.LocalDate;

public class BookReturnResponseDto {
    private Long bookReturnId;
    private String returnNumber;
    private Long bookIssueId;
    private LocalDate returnDate;
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
