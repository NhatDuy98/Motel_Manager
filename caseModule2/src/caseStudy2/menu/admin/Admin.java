package caseStudy2.menu.admin;

import caseStudy2.menu.admin.customerService.CustomerService;
import caseStudy2.menu.admin.displayCustomer.DisplayCustomer;
import caseStudy2.menu.admin.history.History;
import caseStudy2.menu.admin.roomService.RoomService;

import static caseStudy2.Run.scanner;

public class Admin {
    public Admin(){};

    public void running(){
        System.out.println("QUẢN LÝ PHÒNG TRỌ");
        boolean isRunning = true;
        do {
            int choice;
            System.out.println("Menu");
            System.out.printf("%d. Quản lý phòng %-5s %d. Quản lý người thuê %-5s %d. Display %-5s %d. Lịch sử giao dịch %-5s %d. Back %-5s %d. Exit \n",
                    1,"",
                    2,"",
                    3,"",
                    4,"",
                    9,"",
                    0);
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    RoomService roomService = new RoomService();
                    roomService.running();
                    break;
                case 2:
                    CustomerService customerService = new CustomerService();
                    customerService.running();
                    break;
                case 3:
                    DisplayCustomer display = new DisplayCustomer();
                    display.running();
                    break;
                case 4:
                    History history = new History();
                    history.running();
                    break;
                case 9:
                    isRunning = false;
                    break;
                case 0:
                    System.out.println("Hẹn gặp lại");
                    System.out.println("----------------------------------------------");
                    System.exit(0);
                    break;
            }
        } while (isRunning);
    }
}
