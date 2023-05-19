package caseStudy.option.menuOption;

import caseStudy.option.clazz.Customer;
import caseStudy.option.clazz.Room;
import caseStudy.option.format.ReadWrite;

import java.util.ArrayList;
import java.util.List;

import static caseStudy.option.menuOption.CustomerService.listOfRoom;

public class ListOfCustomer {
    private static final String path = "data\\customer.csv";
    private List<Room> rooms = listOfRoom.findAll();

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
        if (customers.size() >= rooms.size()) {
            System.out.println("Hết phòng không thể thêm");
            System.out.println("----------------------------------------------");
        }
        ReadWrite.write(path , customers);
    }
}
