package caseStudy2.menu.admin.roomService;

import caseStudy2.menu.admin.roomService.addRoom.AddRoom;
import caseStudy2.menu.admin.roomService.calculateBill.CalculateBill;
import caseStudy2.menu.admin.roomService.collectMoney.CollectMoney;
import caseStudy2.menu.admin.roomService.displayRoom.DisplayRoom;
import caseStudy2.menu.admin.roomService.fixRoom.FixRoom;

import static caseStudy2.Run.scanner;

public class RoomService {
    public RoomService(){}

    public void running(){
        boolean isRunning1 = true;
        do {
            System.out.println("QUẢN LÝ PHÒNG");
            System.out.printf("%d. Thêm phòng mới %-5s %d. Tính tiền phòng %-5s %d. Thu tiền %-5s %d. Hiển thị %-5s %d. Sửa %-5s %d. Back %-5s %d. Exit \n",
                    1, "",
                    2, "",
                    3, "",
                    4, "",
                    5, "",
                    9, "",
                    0);
            System.out.print("Enter your choice: ");
            int choice1 = scanner.nextInt();
            switch (choice1){
                case 1:
                    AddRoom addRoom = new AddRoom();
                    addRoom.running();
                    break;
                case 2:
                    CalculateBill calculateBill = new CalculateBill();
                    calculateBill.running();
                    break;
                case 3:
                    CollectMoney collectMoney = new CollectMoney();
                    collectMoney.running();
                    break;
                case 4:
                    DisplayRoom displayRoom = new DisplayRoom();
                    displayRoom.running();
                    break;
                case 5:
                    FixRoom fixRoom = new FixRoom();
                    fixRoom.running();
                    break;
                case 9:
                    isRunning1 = false;
                    break;
                case 0:
                    System.out.println("Hẹn gặp lại");
                    System.exit(0);
                    System.out.println("----------------------------------------------");
                    break;
            }
        }while (isRunning1);
    }
}
