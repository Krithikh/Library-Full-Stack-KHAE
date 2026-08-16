package self.learning.backend.lib.mgmt.dataobject;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_fine")
public class FineDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fine_id")
    private Long fineId;
    @Column(name = "fine_number")
    private String fineNumber;
    @Column(name = "fk_book_return")
    private Long bookReturnId;
    @Column(name = "fk_membership")
    private Long membershipId;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "outstanding_amount")
    private BigDecimal outstandingAmount;
    @Column(name = "status")
    private String status;

    public Long getFineId() {
        return fineId;
    }

    public void setFineId(Long fineId) {
        this.fineId = fineId;
    }

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
