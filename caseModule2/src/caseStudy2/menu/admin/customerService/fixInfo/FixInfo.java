package caseStudy2.menu.admin.customerService.fixInfo;


import caseStudy2.menu.admin.customerService.listOfCustomer.ListOfCustomer;
import caseStudy2.menu.admin.displayCustomer.DisplayCustomer;
import caseStudy2.menu.admin.listOfMotel.ListOfMotel;
import caseStudy2.menu.admin.roomService.listOfRoom.ListOfRoom;
import caseStudy2.menu.model.Customer;
import caseStudy2.menu.model.Motel;
import caseStudy2.menu.model.Room;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static caseStudy2.Run.scanner;
import static caseStudy2.menu.format.ReadWrite.read;
import static caseStudy2.menu.format.ReadWrite.write;


public class FixInfo {

    private static ListOfCustomer listOfCustomer = new ListOfCustomer();
    private static ListOfMotel listOfMotel = new ListOfMotel();
    private static ListOfRoom listOfRoom = new ListOfRoom();
    private static DisplayCustomer display = new DisplayCustomer();
    public FixInfo(){}

    public void running(){
        int choice3;
        boolean isRunning = true;
        do {
            System.out.println("Sửa thông tin người thuê");
            System.out.printf("%d. Sửa thông tin %-5s %d. Xóa thông tin %-5s %d. Back %-5s %d. Exit \n", 1, "", 2, "", 9, "", 0);
            System.out.print("Enter your choice: ");
            choice3 = scanner.nextInt();
            switch (choice3) {
                case 1:
                    changeInfo();
                    break;
                case 2:
                    removeCustomer();
                    break;
                case 9:
                    isRunning = false;
                    break;
                case 0:
                    System.out.println("Hẹn gặp lại");
                    System.exit(0);
                    break;
            }
        } while (isRunning);
    }
    private static void removeCustomer() {
        System.out.println("XÓA THÔNG TIN");
        System.out.println("----------------------------------------------");
        display.running();

        System.out.print("Nhập số phòng bạn muốn xóa thông tin: ");
        int numbRoom = scanner.nextInt();

        List<Customer> customers = listOfCustomer.findAll();
        List<Motel> motels = listOfMotel.findAll();


        boolean foundRoom = false;
        boolean foundCustomer = false;

        for (Motel motel : motels) {
            if (motel.getRoomNumb() == numbRoom && !motel.isStatus()) {
                foundRoom = true;
                for (Customer customer : customers) {
                    if (customer.getRoomNumb() == numbRoom) {
                        foundCustomer = true;
                        customer.deleteInfo(numbRoom);
                        System.out.println("Bạn đã xóa thành công");
                        System.out.println("----------------------------------------------");
                        break;
                    }
                }
                for (Room room: listOfRoom.findAll()) {
                    if (room.getNumbRoom() == numbRoom){
                        room.delete(numbRoom);
                        break;
                    }
                }
                break;
            }
        }
        if (!foundRoom) {
            System.out.println("Không tìm thấy phòng có số " + numbRoom);
            System.out.println("----------------------------------------------");
            return;
        }
        if (!foundCustomer) {
            System.out.println("Phòng " + numbRoom + " trống không thể xóa");
            System.out.println("----------------------------------------------");
            return;
        }
        if (customers.isEmpty()) {
            // Nếu danh sách rỗng xóa hết toàn bộ nội dung file
            write("data\\customer.csv", Collections.emptyList());
        }
    }

    private static void changeInfo() {
        System.out.println("Sửa thông tin");
        System.out.println("----------------------------------------------");
        display.running();
        System.out.print("Nhập số phòng bạn muốn sửa: ");
        int roomNumb = scanner.nextInt();
        List<Customer> customers = listOfCustomer.findAll();
        List<Motel> motels = listOfMotel.findAll();

        boolean foundRoom = false;
        boolean foundCustomer = false;

        for (Motel motel : motels) {
            if (motel.getRoomNumb() == roomNumb) {
                foundRoom = true;
                if (!motel.isStatus()) {
                    for (Customer customer : customers) {
                        if (customer.getRoomNumb() == roomNumb) {
                            System.out.println("----------------------------------------------");
                            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", "Phòng", "Tên", "Tuổi", "Địa chỉ", "SĐT", "Ngày sinh", "CCCD");
                            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                                    customer.getRoomNumb(),
                                    customer.getName(),
                                    customer.getAge(),
                                    customer.getAddress(),
                                    customer.getPhoneNumber(),
                                    customer.getDob(),
                                    customer.getCccd());
                            System.out.println("----------------------------------------------");
                            boolean isRunning = true;
                            do {
                                System.out.println("Nhập thông tin bạn muốn sửa");
                                System.out.println("----------------------------------------------");
                                System.out.printf("%d . Sửa tên %-5s %d. Sửa tuổi %-5s %d. Sửa địa chỉ %-5s %d. Sửa SĐT %-5s %d. Sửa ngày sinh %-5s %d. Sửa CCCD %-5s %d. Back %-5s %d. Exit \n",
                                        1, "",
                                        2, "",
                                        3, "",
                                        4, "",
                                        5, "",
                                        6, "",
                                        9, "",
                                        0);
                                System.out.print("Enter your choice: ");
                                int choice4 = scanner.nextInt();
                                List<String> lines = read("data\\customer.csv");
                                List<Customer> newCustomer = new ArrayList<>();
                                for (String line: lines) {
                                    Customer newInfoCustomer = Customer.parseCustomer(line);
                                    if (newInfoCustomer.getRoomNumb() == roomNumb){
                                        switch (choice4){
                                            case 1:
                                                System.out.print("Nhập tên mới: ");
                                                scanner.nextLine();
                                                String newName = scanner.nextLine();
                                                newInfoCustomer.setName(newName);

                                                System.out.println("Bạn đã đổi thành công");
                                                System.out.println("----------------------------------------------");
                                                isRunning = false;
                                                break;
                                            case 2:
                                                System.out.print("Nhập tuổi mới: ");
                                                scanner.nextLine();
                                                String newAge = scanner.nextLine();
                                                newInfoCustomer.setAge(newAge);

                                                System.out.println("Bạn đã đổi thành công");
                                                System.out.println("----------------------------------------------");
                                                isRunning = false;
                                                break;
                                            case 3:
                                                System.out.print("Nhập địa chỉ mới: ");
                                                scanner.nextLine();
                                                String newAddress = scanner.nextLine();
                                                newInfoCustomer.setAddress(newAddress);

                                                System.out.println("Bạn đã đổi thành công");
                                                System.out.println("----------------------------------------------");
                                                isRunning = false;
                                                break;
                                            case 4:
                                                System.out.print("Nhập SĐT mới: ");
                                                scanner.nextLine();
                                                String newPhoneNumber = scanner.nextLine();
                                                newInfoCustomer.setPhoneNumber(newPhoneNumber);

                                                System.out.println("Bạn đã đổi thành công");
                                                System.out.println("----------------------------------------------");
                                                isRunning = false;
                                                break;
                                            case 5:
                                                System.out.print("Nhập ngày sinh mới: ");
                                                scanner.nextLine();
                                                String newDob = scanner.nextLine();
                                                newInfoCustomer.setDob(newDob);

                                                System.out.println("Bạn đã đổi thành công");
                                                System.out.println("----------------------------------------------");
                                                isRunning = false;
                                                break;
                                            case 6:
                                                System.out.print("Nhập CCCD mới: ");
                                                scanner.nextLine();
                                                String newCCCD = scanner.nextLine();
                                                newInfoCustomer.setCccd(newCCCD);

                                                System.out.println("Bạn đã đổi thành công");
                                                System.out.println("----------------------------------------------");
                                                isRunning = false;
                                                break;
                                        }
                                    }
                                    newCustomer.add(newInfoCustomer);
                                }
                                write("data\\customer.csv", newCustomer);
                            } while (isRunning);
                            return;
                        }
                    }
                }
            }
        }
        if (!foundRoom) {
            System.out.println("Không tìm thấy phòng có số " + roomNumb);
            System.out.println("----------------------------------------------");
            return;
        }
        if (!foundCustomer) {
            System.out.println("Phòng " + roomNumb + " trống không thể sửa");
            System.out.println("----------------------------------------------");
        }
    }
}
