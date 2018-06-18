package api.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public boolean addAccount(AccountType type, String id) {
        return accounts.stream().noneMatch(acc -> acc.getIdentifier().equals(id)) && accounts.add(new Account(type, id));
    }

    public boolean removeAccount(String id) {
        return accounts.removeIf(acc -> acc.getIdentifier().equals(id));
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
