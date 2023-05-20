package caseStudy2.menu.admin.roomService.calculateBill;

import caseStudy2.menu.admin.customerService.listOfCustomer.ListOfCustomer;
import caseStudy2.menu.admin.listOfMotel.ListOfMotel;
import caseStudy2.menu.admin.roomService.displayRoom.DisplayRoom;
import caseStudy2.menu.admin.roomService.listOfRoom.ListOfRoom;
import caseStudy2.menu.model.Customer;
import caseStudy2.menu.model.Motel;
import caseStudy2.menu.model.Room;

import java.util.ArrayList;
import java.util.List;

import static caseStudy2.Run.scanner;
import static caseStudy2.menu.format.ReadWrite.read;
import static caseStudy2.menu.format.ReadWrite.write;

public class CalculateBill {
    private ListOfRoom listOfRoom = new ListOfRoom();
    private ListOfMotel listOfMotel = new ListOfMotel();
    private ListOfCustomer listOfCustomer = new ListOfCustomer();
    private DisplayRoom displayRoom = new DisplayRoom();

    public CalculateBill() {
    }

    public void running() {
        System.out.println("TÍNH TIỀN PHÒNG");
        System.out.println("TIỀN PHÒNG THÁNG TRƯỚC");
        displayRoom.running();
        System.out.print("Nhập số phòng bạn cần tính: ");
        int numbRoom = scanner.nextInt();
        boolean foundRoom = false;

        for (Motel motel : listOfMotel.findAll()) {
            if (motel.getRoomNumb() == numbRoom && !motel.isStatus()) {
                for (Room room : listOfRoom.findAll()) {
                    if (room.getNumbRoom() == numbRoom) {
                        foundRoom = true;
                        System.out.println("Nhập số điện đã sử dụng");
                        int newEUse = scanner.nextInt();

                        List<String> lines = read("data\\room.csv");
                        List<Room> newRoom = new ArrayList<>();
                        for (String line : lines) {
                            Room newInfoRoom = Room.parseRoom(line);
                            if (newInfoRoom.getNumbRoom() == numbRoom) {
                                newInfoRoom.setNumbElectricUse(newEUse);
                            }
                            newRoom.add(newInfoRoom);
                        }
                        write("data\\room.csv", newRoom);
                    }
                }
            }
            else {
                System.out.println("Số phòng từ 101 - 110");
                return;
            }
        }
        if (!foundRoom){
            System.out.println("Phòng " + numbRoom + " chưa có thông tin");
            System.out.println("----------------------------------------------");
            return;
        }
        for (Motel motel : listOfMotel.findAll()) {
            if (motel.getRoomNumb() == numbRoom && !motel.isStatus()) {
                for (Room room : listOfRoom.findAll()) {
                    if (room.getNumbRoom() == numbRoom) {
                        int temp = room.getBillRoom();
                        for (Customer customer : listOfCustomer.findAll()) {
                            if (customer.getRoomNumb() == room.getNumbRoom()) {
                                int temp1 = temp + customer.getTemp();
                                List<String> lines = read("data\\customer.csv");
                                List<Customer> newCustomer = new ArrayList<>();
                                for (String line : lines) {
                                    Customer newInfoCustomer = Customer.parseCustomer(line);
                                    if (newInfoCustomer.getRoomNumb() == numbRoom) {
                                        newInfoCustomer.setTemp(temp1);
                                    }
                                    newCustomer.add(newInfoCustomer);
                                }
                                write("data\\customer.csv", newCustomer);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Bạn đã thêm thông tin thành công");
        System.out.println("----------------------------------------------");
    }
}