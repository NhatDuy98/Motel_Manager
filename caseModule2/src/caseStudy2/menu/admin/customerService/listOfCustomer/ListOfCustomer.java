package caseStudy2.menu.admin.customerService.listOfCustomer;

import caseStudy2.menu.model.Customer;
import caseStudy2.menu.format.ReadWrite;

import java.util.ArrayList;
import java.util.List;


public class ListOfCustomer {
    private static final String path = "data\\customer.csv";

    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        List<String> lines = ReadWrite.read(path);
        for (String line : lines) {
            Customer customer = Customer.parseCustomer(line);
            customers.add(customer);
        }
        return customers;
    }

    public void add(Customer customer) {
        List<Customer> customers = findAll();
        customers.add(customer);
        ReadWrite.write(path , customers);
    }
}
