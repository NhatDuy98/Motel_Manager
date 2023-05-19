package caseStudy2.menu.clazz;


import caseStudy2.menu.admin.customerService.listOfCustomer.ListOfCustomer;
import caseStudy2.menu.admin.listOfMotel.ListOfMotel;

import java.util.ArrayList;
import java.util.List;

import static caseStudy2.menu.format.ReadWrite.read;
import static caseStudy2.menu.format.ReadWrite.write;

public class Customer {
    private ListOfMotel listOfMotel = new ListOfMotel();
    private ListOfCustomer listOfCustomer = new ListOfCustomer();
    private String name;
    private String age;
    private String address;
    private String phoneNumber;
    private String dob;
    private String cccd;
    private int roomNumb;
    private boolean status;
    private int temp ;


    public Customer(String name, String age, String address, String phoneNumber, String dob, String cccd, int roomNumb, int temp) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.cccd = cccd;
        this.roomNumb = roomNumb;
        this.temp = temp;
    }

    public static Customer parseCustomer(String raw) {
        String[] fields = raw.split(",");


        String name = fields[0];
        String age = fields[1];
        String address = fields[2];
        String phoneNumber = fields[3];
        String dob = fields[4];
        String cccd = fields[5];
        int numbRoom = Integer.parseInt(fields[6]);
        int temp = Integer.parseInt(fields[7]);

        return new Customer(name, age, address, phoneNumber, dob, cccd, numbRoom,temp);
    }

    public String getName() {
        return this.name;
    }

    public String getAge() {
        return this.age;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getDob() {
        return this.dob;
    }

    public String getCccd() {
        return this.cccd;
    }

    public int getRoomNumb() {
        return roomNumb;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public String setAge(String age) {
        return this.age = age;
    }

    public String setAddress(String address) {
        return this.address = address;
    }

    public String setPhoneNumber(String phoneNumber) {
        return this.phoneNumber = phoneNumber;
    }

    public String setDob(String dob) {
        return this.dob = dob;
    }

    public String setCccd(String cccd) {
        return this.cccd = cccd;
    }

    public int setRoomNumb(int roomNumb) {
        return this.roomNumb = roomNumb;
    }
    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public void deleteInfo(int roomNumb) {
        List<String> lines = read("caseModule2\\data\\customer.csv");
        List<Customer> customers = new ArrayList<>();

        for (String line : lines) {
            Customer customer = Customer.parseCustomer(line);
            if (customer.getRoomNumb() != roomNumb) {
                customers.add(customer);
            }
        }
        write("caseModule2\\data\\customer.csv", customers);

        List<Motel> motels = listOfMotel.findAll();
        for (Motel motel : motels) {
            if (motel.getRoomNumb() == roomNumb) {
                motel.setStatus(false);
                break;
            }
        }
        for (Customer customer:listOfCustomer.findAll()) {
            if (customer.getRoomNumb() == roomNumb){
                customer.setStatus(false);
                break;
            }
        }
    }

    @Override
    public String toString() {
        List<Motel> motels = listOfMotel.findAll();
        String result = "";
        for (Motel motel : motels) {
            if (!motel.isStatus()) {
                result =  this.getName() +
                        "," + this.getAge() +
                        "," + this.getAddress() +
                        "," + this.getPhoneNumber() +
                        "," + this.getDob() +
                        "," + this.getCccd() +
                        "," + this.getRoomNumb() +
                        "," + this.getTemp() +
                        "\n";
                break;
            }
        }
        return result;
    }
}
