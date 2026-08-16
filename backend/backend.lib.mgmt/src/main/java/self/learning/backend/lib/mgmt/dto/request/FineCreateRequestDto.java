package self.learning.backend.lib.mgmt.dto.request;

import java.math.BigDecimal;

public class FineCreateRequestDto {
    private String fineNumber;
    private Long bookReturnId;
    private Long membershipId;
    private BigDecimal amount;
    private BigDecimal outstandingAmount;
    private String status;

    public String getFineNumber() {
        return fineNumber;
    }

    public void setFineNumber(String fineNumber) {
        this.fineNumber = fineNumber;
    }

    public Long getBookReturnId() {
        return bookReturnId;
    }

    public void setBookReturnId(Long bookReturnId) {
        this.bookReturnId = bookReturnId;
    }

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(BigDecimal outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
