package service;

import api.model.AccountType;
import api.model.Bank;
import api.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TransferAgentTest.class})
public class TransferAgentTest {
    private TransferAgent transferAgent;

    private Customer alex;
    private Customer bob;
    private Customer charlie;

    @Before
    public void initialize() {
        alex = new Customer("Alex");
        alex.addAccount(AccountType.BASIC, "a123");
        alex.getAccounts().get(0).deposit(BigDecimal.TEN);
        bob = new Customer("Bob");
        bob.addAccount(AccountType.BASIC, "b123");
        bob.getAccounts().get(0).deposit(BigDecimal.TEN);
        charlie = new Customer("Charlie");
        charlie.addAccount(AccountType.BASIC, "c123");
        charlie.getAccounts().get(0).deposit(BigDecimal.TEN);

        Bank bank1 = new Bank("Bank1");
        bank1.addCustomer(alex);
        bank1.addCustomer(bob);
        Bank bank2 = new Bank("Bank2");
        bank2.addCustomer(charlie);

        transferAgent = new TransferAgent(Arrays.asList(bank1, bank2));
    }

    @Test
    public void intra1() {
        SuccResponse response = transferAgent.transfer("a123", "b123", "1");
        Assert.assertTrue(response.isSuccess());
        Assert.assertEquals(new BigDecimal(9), alex.getAccounts().get(0).getBalance());
        Assert.assertEquals(new BigDecimal(11), bob.getAccounts().get(0).getBalance());
    }

    @Test
    public void inter1() {
        SuccResponse response = transferAgent.transfer("a123", "c123", "1");
        if (response.isSuccess()) {
            Assert.assertEquals(new BigDecimal(4), alex.getAccounts().get(0).getBalance());
            Assert.assertEquals(new BigDecimal(11), charlie.getAccounts().get(0).getBalance());
        } else {
            Assert.assertEquals(new BigDecimal(10), alex.getAccounts().get(0).getBalance());
            Assert.assertEquals(new BigDecimal(10), charlie.getAccounts().get(0).getBalance());
        }
    }
}
