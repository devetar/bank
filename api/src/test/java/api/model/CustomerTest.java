package api.model;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void sameNameAccounts() {
        Customer customer = new Customer("Alex");
        customer.addAccount(AccountType.CURRENT, "123qwe");
        customer.addAccount(AccountType.SAVING, "123qwe");
        Assert.assertEquals(1, customer.getAccounts().size());
    }
}
