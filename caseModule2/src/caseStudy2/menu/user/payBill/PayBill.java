package caseStudy2.menu.user.payBill;

import caseStudy2.menu.admin.customerService.listOfCustomer.ListOfCustomer;
import caseStudy2.menu.admin.history.ListOfHistory;
import caseStudy2.menu.admin.listOfMotel.ListOfMotel;
import caseStudy2.menu.admin.roomService.listOfRoom.ListOfRoom;
import caseStudy2.menu.model.Customer;
import caseStudy2.menu.model.Motel;
import caseStudy2.menu.model.Room;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static caseStudy2.Run.scanner;
import static caseStudy2.menu.format.ReadWrite.*;

public class PayBill {
    private ListOfMotel listOfMotel = new ListOfMotel();
    private ListOfCustomer listOfCustomer = new ListOfCustomer();
    private ListOfRoom listOfRoom = new ListOfRoom();
    private ListOfHistory listOfHistory = new ListOfHistory();

    public PayBill(){}

    public void running(){
        System.out.println("=============THANH TOÁN=============");
        boolean isRunning = true;
        do {
            System.out.print("Nhập phòng bạn muốn thanh toán: ");
            int numbRoom = scanner.nextInt();
            boolean foundCustomer = false;

            for (Motel motel:listOfMotel.findAll()) {
                if (motel.getRoomNumb() == numbRoom && !motel.isStatus()){
                    for (Customer customer:listOfCustomer.findAll()) {
                        if (customer.getRoomNumb() == numbRoom){
                            foundCustomer = true;
                            for (Room room:listOfRoom.findAll()) {
                                if (room.getNumbRoom() == numbRoom){
                                    System.out.printf("Phòng %s \n Tiền điện: %s \n Tiền nước: %s \n Tổng bill: %s \n Tiền chưa thanh toán: %s \n ",
                                            motel.getRoomNumb(),
                                            room.getMoneyElectric(),
                                            room.getMoneyWater(),
                                            room.getBillRoom(),
                                            customer.getTemp());
                                }
                            }
                        }
                    }
                }
            }
            if (!foundCustomer){
                System.out.println("Phòng " + numbRoom + " chưa có thông tin");
                System.out.println("----------------------------------------------");
                return;
            }

            System.out.printf("%d. Xác nhận thanh toán %-5s %d. Back %-5s %d. Exit \n",
                    1,"",
                    9,"",
                    0);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.print("Nhập số tiền: ");
                    int money = scanner.nextInt();
                    boolean checkMoney = false;

                    for (Customer customer:listOfCustomer.findAll()) {
                        if (customer.getRoomNumb() == numbRoom && customer.getTemp() >= money){
                            checkMoney = true;
                            int temp = customer.getTemp() - money;

                            List<String> lines = read("data\\customer.csv");
                            List<Customer> newCustomers = new ArrayList<>();
                            for (String line:lines) {
                                Customer newInfoCustomer = Customer.parseCustomer(line);
                                if (newInfoCustomer.getRoomNumb() == numbRoom){
                                    newInfoCustomer.setTemp(temp);
                                }
                                newCustomers.add(newInfoCustomer);
                            }
                            write("data\\customer.csv", newCustomers);
                            System.out.println("Bạn đã thanh toán thành công");
                            System.out.println("----------------------------------------------");
                        }
                    }
                    if (!checkMoney){
                        System.out.println("Số tiền bạn nhập lớn hơn số tiền cần thu");
                        System.out.println("----------------------------------------------");
                        return;
                    }
                    LocalDateTime currentLocalDateTime = LocalDateTime.now();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("\"yyyy-MM-dd HH:mm\"");
                    String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);
                    String str= numbRoom + "," + money + "," + "Chuyển khoản"  + "," + formattedDateTime + "\n"  ;
                    listOfHistory.add(str);
                    write("data\\history.csv",listOfHistory.findAll());
                    isRunning = false;
                    break;
                case 9:
                    isRunning = false;
                    break;
                case 0:
                    System.out.println("Hẹn gặp lại");
                    System.out.println("----------------------------------------------");
                    break;
            }
        }while (isRunning);
    }
}
