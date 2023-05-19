package caseStudy2.menu.admin.roomService.collectMoney;

import caseStudy2.menu.admin.customerService.listOfCustomer.ListOfCustomer;
import caseStudy2.menu.admin.history.ListOfHistory;
import caseStudy2.menu.admin.listOfMotel.ListOfMotel;
import caseStudy2.menu.admin.roomService.displayRoom.DisplayRoom;
import caseStudy2.menu.admin.roomService.listOfRoom.ListOfRoom;
import caseStudy2.menu.clazz.Customer;
import caseStudy2.menu.clazz.Motel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static caseStudy2.Run.scanner;
import static caseStudy2.menu.format.ReadWrite.read;
import static caseStudy2.menu.format.ReadWrite.write;

public class CollectMoney {
    private ListOfRoom listOfRoom = new ListOfRoom();
    private ListOfMotel listOfMotel = new ListOfMotel();
    private ListOfCustomer listOfCustomer = new ListOfCustomer();
    private ListOfHistory listOfHistory = new ListOfHistory();
    private DisplayRoom displayRoom = new DisplayRoom();
    public CollectMoney(){}

    public void running(){
        System.out.println("THU TIỀN PHÒNG");
        displayRoom.running();
        System.out.println("Nhập số phòng cần thu tiền");
        int numbRoom = scanner.nextInt();
        System.out.println("----------------------------------------------");
        boolean checkMoney = false;

        for (Motel motel:listOfMotel.findAll()){
            if (motel.getRoomNumb() == numbRoom && !motel.isStatus()){
                for (Customer customer:listOfCustomer.findAll()) {
                    if (customer.getRoomNumb() == numbRoom){
                        System.out.printf("%-15s %-15s %-15s \n", "Phòng", "Tên", "Tiền chưa thanh toán");
                        System.out.printf("%-15s %-15s %-15s \n",
                                customer.getRoomNumb(),
                                customer.getName(),
                                customer.getTemp());
                        System.out.println("----------------------------------------------");
                        System.out.print("Nhập số tiền: ");
                        int money = scanner.nextInt();

                        if (money <= customer.getTemp()){
                            checkMoney = true;
                            int temp = customer.getTemp() - money;

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
                            LocalDateTime currentLocalDateTime = LocalDateTime.now();
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("\"yyyy-MM-dd HH:mm\"");
                            String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);
                            String history = numbRoom + "," + money + "," + "Tiền mặt" + "," + formattedDateTime + "\n";
                            listOfHistory.add(history);
                            write("data\\history.csv",listOfHistory.findAll());
                        }
                    }
                }
            }
        }
        if (!checkMoney){
            System.out.println("Số tiền thu lớn hơn số tiền cần thanh toán");
            System.out.println("----------------------------------------------");
            return;
        }
        System.out.println("Bạn đã thu tiền thành công");
        System.out.println("----------------------------------------------");
    }
}
