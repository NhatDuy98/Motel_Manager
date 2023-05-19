package caseStudy.option.menuOption;

import caseStudy.option.clazz.Customer;
import caseStudy.option.clazz.Room;

import static caseStudy.option.menuOption.CustomerService.listOfCustomer;
import static caseStudy.option.menuOption.CustomerService.listOfRoom;


public class Display {
    public static void display() {

        System.out.println("Danh sách khách thuê phòng: ");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n","Phòng", "Tên", "Tuổi", "Địa chỉ" , "SĐT" , "Ngày sinh" , "CCCD");

        for (Room room:listOfRoom.findAll()) {
            for (Customer customer: listOfCustomer.findAll()) {
                if (customer.getRoomNumb() == room.getRoomNumb()){
                    System.out.printf("%-15s: %-15s %-15s %-15s %-15s %-15s %-15s\n",
                            room.getRoomNumb(),
                            customer.getName(),
                            customer.getAge(),
                            customer.getAddress(),
                            customer.getPhoneNumber(),
                            customer.getDob(),
                            customer.getCccd());
                    room.setStatus(true);
                }
            }
            if (!room.isStatus()){
                System.out.println(room.getRoomNumb() + ": Trống");
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }
}
