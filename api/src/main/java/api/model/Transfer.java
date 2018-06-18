package api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transfer {
    private LocalDateTime time;
    private Account fromAcc;
    private Account toAcc;
    private BigDecimal amount;
    private boolean successful;

    public Transfer(LocalDateTime time, Account fromAcc, Account toAcc, BigDecimal amount, boolean successful) {
        this.time = time;
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        this.amount = amount;
        this.successful = successful;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Account getFromAcc() {
        return fromAcc;
    }

    public Account getToAcc() {
        return toAcc;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
