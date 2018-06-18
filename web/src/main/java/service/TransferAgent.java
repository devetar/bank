package service;

import api.model.Account;
import api.model.Bank;
import api.model.Customer;
import api.model.Transfer;
import api.model.TransferType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TransferAgent {

    private final AtomicLong counter = new AtomicLong();

    private List<Bank> banks;

    //constructor for the test
    public TransferAgent(List<Bank> banks) {
        this.banks = banks;
    }

    @RequestMapping("/transfer")
    public SuccResponse transfer(@RequestParam(value = "fromAcc") String fromAcc, @RequestParam(value = "toAcc") String toAcc, @RequestParam(value = "amount") String amount) {
        BigDecimal amt;
        try {
            amt = new BigDecimal(amount);
        } catch (NumberFormatException e) {
            return new SuccResponse(counter.incrementAndGet(), false, "Wrong amount value - not a number");
        }
        if (amt.compareTo(BigDecimal.ZERO) < 1) {
            return new SuccResponse(counter.incrementAndGet(), false, "Wrong amount value - must be positive");
        }
        Account from = findAccount(fromAcc);
        if (from == null) {
            return new SuccResponse(counter.incrementAndGet(), false, "Wrong fromAcc");
        }
        Account to = findAccount(toAcc);
        if (to == null) {
            return new SuccResponse(counter.incrementAndGet(), false, "Wrong toAcc");
        }
        Bank fromBank = findBankByAcc(fromAcc);
        Bank toBank = findBankByAcc(toAcc);
        if (fromBank == null || toBank == null) {
            return new SuccResponse(counter.incrementAndGet(), false, "Wrong account ids");
        }
        boolean success = true;
        final LocalDateTime now = LocalDateTime.now();
        if (fromBank.getName().equals(toBank.getName())) {
            Transfer transfer = new Transfer(now, from, to, amt, true);
            from.addTransfer(transfer);
            to.addTransfer(transfer);
        } else {
            Random random = new Random();
            success = (random.nextInt(100) + 1) < TransferType.INTER_BANK.getSuccessRate() && amt.compareTo(TransferType.INTER_BANK.getLimit()) < 1;
            Transfer transfer = new Transfer(now, from, to, amt, success);
            from.addTransfer(transfer);
            to.addTransfer(transfer);
            if (success) {
                from.chargeCommision(TransferType.INTER_BANK.getCommision());
            }

        }

        return new SuccResponse(counter.incrementAndGet(), success, "Successfull transfer");
    }

    private Bank findBankByAcc(String accountId) {
        for (Bank bank : banks) {
            for (Customer customer : bank.getCustomers()) {
                for (Account account : customer.getAccounts()) {
                    if (account.getIdentifier().equals(accountId)) {
                        return bank;
                    }
                }
            }
        }
        return null;
    }

    private Account findAccount(String accountId) {
        for (Bank bank : banks) {
            for (Customer customer : bank.getCustomers()) {
                for (Account account : customer.getAccounts()) {
                    if (account.getIdentifier().equals(accountId)) {
                        return account;
                    }
                }
            }
        }
        return null;
    }
}
