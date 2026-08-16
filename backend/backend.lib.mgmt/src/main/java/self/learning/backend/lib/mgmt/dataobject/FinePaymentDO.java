package self.learning.backend.lib.mgmt.dataobject;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_fine_payment")
public class FinePaymentDO {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fine_payment_id") private Long finePaymentId;
    @Column(name = "payment_number") private String paymentNumber;
    @Column(name = "fk_fine") private Long fineId;
    @Column(name = "amount") private BigDecimal amount;
    @Column(name = "paid_on") private LocalDate paidOn;
    @Column(name = "status") private String status;
    public Long getFinePaymentId() { return finePaymentId; } public void setFinePaymentId(Long v) { finePaymentId=v; }
    public String getPaymentNumber() { return paymentNumber; } public void setPaymentNumber(String v) { paymentNumber=v; }
    public Long getFineId() { return fineId; } public void setFineId(Long v) { fineId=v; }
    public BigDecimal getAmount() { return amount; } public void setAmount(BigDecimal v) { amount=v; }
    public LocalDate getPaidOn() { return paidOn; } public void setPaidOn(LocalDate v) { paidOn=v; }
    public String getStatus() { return status; } public void setStatus(String v) { status=v; }
}
