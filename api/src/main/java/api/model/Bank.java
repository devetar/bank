package api.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Customer> customers;

    public Bank(String name) {
        this.name = name;
        this.customers = new ArrayList<>();
    }

    public boolean addCustomer(Customer newCustomer) {
        return customers.stream().noneMatch(customer -> customer.getName().equals(newCustomer.getName())) && customers.add(newCustomer);
    }

    public String getName() {
        return name;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
