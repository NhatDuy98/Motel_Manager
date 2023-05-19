package caseStudy2.menu.user;

import caseStudy2.menu.admin.customerService.listOfCustomer.ListOfCustomer;
import caseStudy2.menu.admin.listOfMotel.ListOfMotel;
import caseStudy2.menu.admin.roomService.listOfRoom.ListOfRoom;
import caseStudy2.menu.user.payBill.PayBill;

import static caseStudy2.Run.scanner;

public class User {
    private ListOfMotel listOfMotel = new ListOfMotel();
    private ListOfCustomer listOfCustomer = new ListOfCustomer();
    private ListOfRoom listOfRoom = new ListOfRoom();
    public User(){}

    public void running(){
        System.out.println("==========GIAO DIỆN NGƯỜI DÙNG==========");
        boolean isRunning = true;

        do {
            System.out.printf("%d. Thanh toán %-5s %d. Back %-5s %d. Exit \n" ,
                    1,"",
                    9,"",
                    0);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    PayBill payBill = new PayBill();
                    payBill.running();
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
        }while (isRunning);
    }
}
