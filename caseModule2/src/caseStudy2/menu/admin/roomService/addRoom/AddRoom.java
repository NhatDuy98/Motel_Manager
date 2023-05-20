package caseStudy2.menu.admin.roomService.addRoom;

import caseStudy2.menu.admin.customerService.listOfCustomer.ListOfCustomer;
import caseStudy2.menu.admin.listOfMotel.ListOfMotel;
import caseStudy2.menu.admin.roomService.listOfRoom.ListOfRoom;
import caseStudy2.menu.model.Customer;
import caseStudy2.menu.model.Motel;
import caseStudy2.menu.model.Room;
import caseStudy2.menu.format.RetryInput;

import static caseStudy2.Run.scanner;
import static caseStudy2.menu.format.ReadWrite.read;

import java.util.ArrayList;
import java.util.List;


import static caseStudy2.menu.format.ReadWrite.write;

public class AddRoom {
    private ListOfRoom listOfRoom = new ListOfRoom();
    private ListOfMotel listOfMotel = new ListOfMotel();
    private ListOfCustomer listOfCustomer = new ListOfCustomer();
    public AddRoom(){}
    public void running(){
        System.out.println("THÊM PHÒNG MỚI");
        System.out.println("----------------------------------------------");
        int numbRoom = scanner.nextInt();
        System.out.println("----------------------------------------------");
        boolean foundCustomer = false;

        for (Motel motel:listOfMotel.findAll()) {
            if (motel.getRoomNumb() == numbRoom && !motel.isStatus()){
                for (Room room:listOfRoom.findAll()) {
                    if (room.getNumbRoom() == numbRoom){
                        System.out.println("Phòng " + numbRoom + " đã có thông tin");
                        return;
                    }
                    else {
                        for (Customer customer:listOfCustomer.findAll()) {
                            if (customer.getRoomNumb() == numbRoom){
                                foundCustomer = true;

                                int numbPersonLive = inputNumbPersonLive();
                                int numbElectric = inputNumbElectric();
                                int numbWater = inputNumbWater();
                                int moneyRoom = inputMoneyRoom();

                                Room newRoom = new Room(numbRoom, numbPersonLive , numbElectric , numbWater , 0 , moneyRoom );

                                int temp = newRoom.getBillRoom();
                                listOfRoom.add(newRoom);

                                List<String> lines = read("data\\customer.csv");
                                List<Customer> newCustomer = new ArrayList<>();
                                for (String line:lines) {
                                    Customer newInfoCustomer = Customer.parseCustomer(line);
                                    if (newInfoCustomer.getRoomNumb() == numbRoom){
                                        newInfoCustomer.setTemp(temp);
                                    }
                                    newCustomer.add(newInfoCustomer);
                                }
                                write("data\\customer.csv", newCustomer);
                            }
                        }
                    }
                }
            }
            else {
                System.out.println("Số phòng từ 101 - 110");
                return;
            }
        }
        if (!foundCustomer){
            System.out.println("Phòng " + numbRoom + " chưa có thông tin");
            System.out.println("----------------------------------------------");
            return;
        }

        System.out.println("Bạn đã thêm thông tin thành công");
        System.out.println("----------------------------------------------");

        for (Motel motel : listOfMotel.findAll()) {
            if (motel.getRoomNumb() == numbRoom) {
                motel.setStatus(true);
                break;
            }
        }
        for (Room room:listOfRoom.findAll()) {
            if (room.getNumbRoom() == numbRoom)
                room.setStatus(true);
            break;
        }

    }
    private static int inputNumbRoom(){
        do {
            try {
                String numbRoom = RetryInput.retryString("Số phòng");
                return Integer.parseInt(numbRoom);
            }catch (Exception exception){
                System.out.println("Vui lòng nhập đúng định dạng số");
                System.out.println("----------------------------------------------");
            }
        } while (true);
    }

    private static int inputNumbPersonLive(){
        do {
            try {
                String numbPersonLive = RetryInput.retryString("Số người ở");
                return Integer.parseInt(numbPersonLive);
            }catch (Exception exception){
                System.out.println("Vui lòng nhập đúng định dạng số");
                System.out.println("----------------------------------------------");
            }
        }while (true);
    }
    private static int inputNumbElectric(){
        do {
            try {
                String numbElectric = RetryInput.retryString("Giá điện");
                return Integer.parseInt(numbElectric);
            }catch (Exception exception){
                System.out.println("Vui lòng nhập đúng định dạng số");
                System.out.println("----------------------------------------------");
            }
        }while (true);
    }
    private static int inputNumbWater(){
        do {
            try {
                String numbWater = RetryInput.retryString("Giá nước");
                return Integer.parseInt(numbWater);
            }catch (Exception exception){
                System.out.println("Vui lòng nhập đúng định dạng số");
                System.out.println("----------------------------------------------");
            }
        }while (true);
    }
    private static int inputMoneyRoom(){
        do {
            try {
                String moneyRoom = RetryInput.retryString("Tiền phòng");
                return Integer.parseInt(moneyRoom);
//                return moneyRoom;
            }catch (Exception exception){
                System.out.println("Vui lòng nhập đúng định dạng số");
                System.out.println("----------------------------------------------");
            }
        }while (true);
    }
}
