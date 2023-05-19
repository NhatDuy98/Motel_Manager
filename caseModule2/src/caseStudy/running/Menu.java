package caseStudy.running;

import java.util.Scanner;

import static caseStudy.option.menuOption.RoomService.roomService;
import static caseStudy.option.menuOption.CustomerService.customerService;
import static caseStudy.option.menuOption.Display.display;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("QUẢN LÝ PHÒNG TRỌ");
        do {
            int choice;
            System.out.println("Menu");
            System.out.printf("%d. Quản lý phòng \t\t\t %d. Quản lý người thuê \t\t\t %d. Display \t\t\t %d. Exit \n", 1, 2, 3, 0);
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    roomService();
                    break;
                case 2:
                    customerService();
                    break;
                case 3:
                    display();
                    break;
                case 0:
                    System.out.println("Hẹn gặp lại");
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}


