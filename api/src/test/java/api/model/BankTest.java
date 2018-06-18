package api.model;

import org.junit.Assert;
import org.junit.Test;

public class BankTest {

    @Test
    public void sameNameClients() {
        Bank bank = new Bank("tBank");
        bank.addCustomer(new Customer("Alex"));
        bank.addCustomer(new Customer("Alex"));
        Assert.assertEquals(1, bank.getCustomers().size());
    }

    @Test
    public void differentNameClients() {
        Bank bank = new Bank("tBank");
        bank.addCustomer(new Customer("Alex"));
        bank.addCustomer(new Customer("Bob"));
        Assert.assertEquals(2, bank.getCustomers().size());
    }
}
