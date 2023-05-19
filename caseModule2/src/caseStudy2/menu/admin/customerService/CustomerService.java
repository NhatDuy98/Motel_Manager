package caseStudy2.menu.admin.customerService;


import caseStudy2.menu.admin.customerService.addCustomer.AddCustomer;
import caseStudy2.menu.admin.customerService.fixInfo.FixInfo;

import static caseStudy2.Run.scanner;

public class CustomerService {
    public CustomerService(){}
    public void running(){
        int choice2;
        do {
            System.out.println("Quản lý người thuê");
            System.out.printf("%d. Thêm thông tin người thuê \t\t\t %d. Sửa thông tin người thuê \t\t\t %d. Back \t\t\t %d. Exit \n", 1, 2, 9, 0);
            System.out.print("Enter your choice: ");
            choice2 = scanner.nextInt();
            switch (choice2) {
                case 1:
                    AddCustomer addCustomer = new AddCustomer();
                    addCustomer.running();
                    break;
                case 2:
                    FixInfo fixInfo = new FixInfo();
                    fixInfo.running();
                case 9:
                    choice2 = -1;
                    break;
                case 0:
                    System.out.println("Hẹn gặp lại");
                    System.out.println("----------------------------------------------");
                    System.exit(0);
                    break;
            }
        } while (choice2 != -1);
    }
}
