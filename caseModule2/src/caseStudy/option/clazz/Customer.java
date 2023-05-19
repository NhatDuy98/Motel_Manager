package caseStudy.option.clazz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static caseStudy.option.format.ReadWrite.read;
import static caseStudy.option.format.ReadWrite.write;
import static caseStudy.option.menuOption.CustomerService.listOfRoom;

public class Customer {
    private String name;
    private String age;
    private String address;
    private String phoneNumber;
    private String dob;
    private String cccd;
    private int roomNumb;

    public Customer() {
    }

    public Customer(String name, String age, String address, String phoneNumber, String dob, String cccd, int roomNumb) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.cccd = cccd;
        this.roomNumb = roomNumb;
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
        return new Customer(name, age, address, phoneNumber, dob, cccd, numbRoom);
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


    public void deleteInfo(int roomNumb) {
        List<String> lines = read("data\\customer.csv");
        List<Customer> customers = new ArrayList<>();

        for (String line : lines) {
            Customer customer = Customer.parseCustomer(line);
            if (customer.getRoomNumb() != roomNumb) {
                customers.add(customer);
            }
        }
        write("data\\customer.csv", customers);

        List<Room> rooms = listOfRoom.findAll();
        for (Room room : rooms) {
            if (room.getRoomNumb() == roomNumb) {
                room.setStatus(false);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return roomNumb == customer.roomNumb
                && Objects.equals(name, customer.name)
                && Objects.equals(age, customer.age)
                && Objects.equals(address, customer.address)
                && Objects.equals(phoneNumber, customer.phoneNumber)
                && Objects.equals(dob, customer.dob)
                && Objects.equals(cccd, customer.cccd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, address, phoneNumber, dob, cccd, roomNumb);
    }

    @Override
    public String toString() {
        List<Room> rooms = listOfRoom.findAll();
        String result = "";
        for (Room room : rooms) {
            if (!room.isStatus()) {
                result = this.getName() +
                        "," + this.getAge() +
                        "," + this.getAddress() +
                        "," + this.getPhoneNumber() +
                        "," + this.getDob() +
                        "," + this.getCccd() +
                        "," + this.getRoomNumb() +
                        "\n";
                break;
            }
        }
        return result;
    }

    //    @Override
//    public String toString() {
//        List<Room> rooms = listOfRoom.findAll();
//        String result = "" ;
//        for (Room room:rooms) {
//            if (!room.isStatus()){
//                result += String.format("%-15s: %-15s %-15s %-15s %-15s %-15s %-15s\n",
//                        room.getRoomNumb(),
//                        getName(),
//                        getAge(),
//                        getAddress(),
//                        getPhoneNumber(),
//                        getDob(),
//                        getCccd());
//            }
//        }
//        return result;
////        return result;
////        return "Bạn " + this.getName() + " có thông tin là: "
////                + this.getAge() + " tuổi \n\t\t\t\t"
////                + "Địa chỉ: " + this.getAddress() + "\n\t\t\t\t"
////                + "SĐT: " + this.getPhoneNumber() + "\n\t\t\t\t"
////                + "Date of birth: " + this.getDob() + "\n\t\t\t\t"
////                + "Số CCCD: " + this.getCccd() + "\n";
//    }
}
