package api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private AccountType type;
    private String identifier;
    private BigDecimal balance;
    private List<Transfer> transfers;

    public Account(AccountType type, String identifier) {
        this.type = type;
        this.identifier = identifier;
        this.balance = BigDecimal.ZERO;
        transfers = new ArrayList<>();
    }

    public void addTransfer(Transfer transfer) {
        transfers.add(transfer);
        if (transfer.isSuccessful()) {
            if (identifier.equals(transfer.getFromAcc().getIdentifier())) {
                balance = balance.subtract(transfer.getAmount());
            } else {
                balance = balance.add(transfer.getAmount());
            }
        }
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void chargeCommision(BigDecimal commision) {
        balance = balance.subtract(commision);
    }

    public AccountType getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }
}
