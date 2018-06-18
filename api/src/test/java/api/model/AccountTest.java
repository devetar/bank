package api.model;

import org.junit.Assert;
import org.junit.Test;

public class AccountTest {
    @Test
    public void emptyTransfers() {
        Account account = new Account(AccountType.BASIC, "I");
        Assert.assertTrue(account.getTransfers().isEmpty());
    }


}
