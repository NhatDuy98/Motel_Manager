package caseStudy.option.menuOption;

import caseStudy.option.clazz.Customer;
import caseStudy.option.clazz.Room;
import caseStudy.option.clazz.RoomManager;
import caseStudy.option.format.RetryInput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static caseStudy.option.format.ReadWrite.read;
import static caseStudy.option.format.ReadWrite.write;
import static caseStudy.option.menuOption.CustomerService.*;

public class RoomService {
    public static ListOfMoney listOfMoney = new ListOfMoney();

    public static void roomService() {
        Scanner scanner = new Scanner(System.in);
        int choice1;
        do {
            System.out.println("QUẢN LÝ PHÒNG");
            System.out.printf("%d. Tính tiền phòng %-5s %d. Thu tiền %-5s %d. Hiển thị %-5s %d. Back %-5s %d. Exit \n",
                    1, "",
                    2, "",
                    3, "",
                    9, "",
                    0);
            System.out.print("Enter your choice: ");
            choice1 = scanner.nextInt();
            switch (choice1) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    System.out.println("Nhập số phòng bạn muốn thu");
                    int numbRoom = scanner.nextInt();

                    List<String> lines = read("data\\roomManager.csv");
                    List<RoomManager> newRoomInfo = new ArrayList<>();
                    for (String line : lines) {
                        RoomManager newRoomManager = RoomManager.parseRoomManager(line);
                        if (newRoomManager.getRoomNumb() == numbRoom) {
                            System.out.println("Nhập số tiền bạn muốn thu");
                            int money = scanner.nextInt();
                            int moneyTemp = newRoomManager.getNewBillRoom() - money;
                            newRoomManager.setNewBillRoom(moneyTemp);
                            System.out.println("Đã thu tiền thành công");
                        }
                        newRoomInfo.add(newRoomManager);
                    }
                    write("data\\roomManager.csv", newRoomInfo);
                    File file = new File("data\\roomManager.csv");
                    file.delete();
                    File fileTemp = new File("data\\roomManager_temp.csv");
                    fileTemp.renameTo(file);
                    break;
                case 3:
                    System.out.println("Danh sách tiền cần thu");
                    System.out.println("----------------------------------------------");
                    System.out.printf("%-15s %-15s %-25s %-25s %-20s %-15s %-15s %-15s\n",
                            "Phòng",
                            "Số người",
                            "Điện tháng trước",
                            "Điện tháng này",
                            "Tổng nước",
                            "Tiền phòng",
                            "Tổng",
                            "Tiền nợ");

                    for (Room room : listOfRoom.findAll()) {
                        for (RoomManager roomManager : listOfMoney.findAll()) {
                            if (roomManager.getRoomNumb() == room.getRoomNumb()) {
                                System.out.printf("%-15s: %-15s %-25s %-25s %-20s %-15s %-15s %-15s\n",
                                        room.getRoomNumb(),
                                        roomManager.getNumbPersonLive(),
                                        roomManager.getNumbElectricLast(),
                                        roomManager.getNumbElectricNow(),
                                        roomManager.getNumbWaterUse(),
                                        roomManager.getNumbMoneyRoom(),
                                        roomManager.getNewBillRoom(),
                                        room.getMoneyTemp());
//                                roomManager.getTotalBill(),  %-15s

                                room.setStatus(true);
                                roomManager.setStatus(true);
                            }
                        }
                        if (!room.isStatus()) {
                            System.out.println(room.getRoomNumb() + ": Trống");
                        }
                    }
                    break;
                case 9:
                    choice1 = -1;
                    break;
                case 0:
                    System.out.println("Hẹn gặp lại");
                    System.exit(0);
                    break;
            }
        } while (choice1 != -1);
    }

    private static String inputNumbRoom() {
        do {
            try {
                String numbRoom = RetryInput.retryString("Số phòng");
                Integer.parseInt(numbRoom);
                return numbRoom;
            } catch (Exception exception) {
                System.out.println("Vui lòng nhập đúng định dạng số.");
                System.out.println("----------------------------------------------");
            }
        } while (true);
    }

    private static String inputPersonLive() {
        do {
            try {
                String numbPersonLive = RetryInput.retryString("Số người ở");
                Integer.parseInt(numbPersonLive);
                return numbPersonLive;
            } catch (Exception exception) {
                System.out.println("Vui lòng nhập đúng định dạng số.");
                System.out.println("----------------------------------------------");
            }
        } while (true);
    }

    private static String inputELast() {
        do {
            try {
                String numbELast = RetryInput.retryString("Số điện tháng trước");
                Integer.parseInt(numbELast);
                return numbELast;
            } catch (Exception exception) {
                System.out.println("Vui lòng nhập đúng định dạng số.");
                System.out.println("----------------------------------------------");
            }
        } while (true);
    }

    private static String inputENow() {
        do {
            try {
                String numbENow = RetryInput.retryString("Số điện tháng này");
                Integer.parseInt(numbENow);
                return numbENow;
            } catch (Exception exception) {
                System.out.println("Vui lòng nhập đúng định dạng số.");
                System.out.println("----------------------------------------------");
            }
        } while (true);
    }

    private static String inputMoneyRoom() {
        do {
            try {
                String numbMoneyRoom = RetryInput.retryString("Tiền phòng");
                Integer.parseInt(numbMoneyRoom);
                return numbMoneyRoom;
            } catch (Exception exception) {
                System.out.println("Vui lòng nhập đúng định dạng số.");
                System.out.println("----------------------------------------------");
            }
        } while (true);
    }

    private static void addNewRoom() {
        System.out.println("THÊM PHÒNG MỚI");
        int numbRoom = Integer.parseInt(inputNumbRoom());
        for (RoomManager roomManager : listOfMoney.findAll()) {
            if (roomManager.getRoomNumb() == numbRoom) {
                System.out.println("Phòng đã tồn tại");
                System.out.println("----------------------------------------------");
                return;
            }
        }
        System.out.println("----------------------------------------------");

        int numbPersonLive = Integer.parseInt(inputPersonLive());
        int numbElectricLast = Integer.parseInt(inputELast());
        int numbElectricNow = Integer.parseInt(inputENow());
        int moneyRoom = Integer.parseInt(inputMoneyRoom());

        RoomManager newRoomManager = new RoomManager(numbRoom, numbPersonLive, numbElectricLast, numbElectricNow, moneyRoom);
        int temp = newRoomManager.getNewBillRoom();
        listOfMoney.add(newRoomManager);
        for (RoomManager roomManager : listOfMoney.findAll()) {
            if (roomManager.getRoomNumb() == numbRoom) {
                roomManager.setStatus(true);
            }
        }
        for (Room room : listOfRoom.findAll()) {
            if (room.getRoomNumb() == numbRoom) {
                List<String> lines = read("data\\room.csv");
                List<RoomManager> newRoomInfo = new ArrayList<>();
                for (String line : lines) {
                    Room newInfoRoom = Room.parseRoom(line);
                    if (newInfoRoom.getRoomNumb() == numbRoom) {
                        newInfoRoom.setMoneyTemp(temp);
                    }
                    newRoomInfo.add(newRoomManager);
                }
                write("data\\room.csv", newRoomInfo);
            }
            File file = new File("data\\room.csv");
            file.delete();
            File fileTemp = new File("data\\room_temp.csv");
            fileTemp.renameTo(file);
        }
        System.out.println("Bạn đã thêm thành công");
        System.out.println("----------------------------------------------");
    }




    public static void addRoom() {
        System.out.println("TÍNH TIỀN PHÒNG");
        System.out.println("----------------------------------------------");
        int choice2;
        boolean isRunning2 = true;
        do {
            System.out.printf("%d. Thêm phòng mới %-5s %d. Tính tiền phòng %-5s %d. Back %-5s %d. Exit \n",
                    1, "",
                    2, "",
                    9, "",
                    0);
            System.out.print("Enter your choice: ");
            choice2 = scanner.nextInt();
            switch (choice2) {
                case 1:
                    addNewRoom();
                    break;
                case 2:
                    System.out.println("TÍNH TIỀN PHÒNG");
                    System.out.println("Nhập số phòng");
                    int numbRoom = Integer.parseInt(inputNumbRoom());
                    int temp;
                    for (RoomManager roomManager : listOfMoney.findAll()) {
                        if (roomManager.getRoomNumb() == numbRoom) {
//                            temp = roomManager.getMoneyTemp();
                            List<String> lines = read("data\\roomManager.csv");
                            List<RoomManager> newRoomInfo = new ArrayList<>();
                            for (String line : lines) {
                                RoomManager newRoomManager = RoomManager.parseRoomManager(line);
                                if (newRoomManager.getRoomNumb() == numbRoom) {
                                    int numbELast = Integer.parseInt(inputELast());
                                    newRoomManager.setNumbElectricLast(numbELast);
                                    int numbENow = Integer.parseInt(inputENow());
                                    newRoomManager.setNumbElectricNow(numbENow);
                                    System.out.println("Đã thêm thông tin thành công");
//                                    int moneyTemp = temp + newRoomManager.getNewBillRoom();
//                                    newRoomManager.setMoneyTemp(moneyTemp);
                                }
                                newRoomInfo.add(newRoomManager);
                            }
                            write("data\\roomManager.csv", newRoomInfo);
                        }
                        File file = new File("data\\roomManager.csv");
                        file.delete();
                        File fileTemp = new File("data\\roomManager_temp.csv");
                        fileTemp.renameTo(file);
                    }
//                    for (RoomManager roomManager:listOfMoney.findAll()) {
//                        if (roomManager.getRoomNumb() == numbRoom && roomManager == null){
//                            System.out.println("Phòng hiện đang trống");
//                            System.out.println("----------------------------------------------");
//                            return;
//                        }
//                    }

                    break;
                case 9:
                    isRunning2 = false;
                    break;
                case 0:
                    System.out.println("Hẹn gặp lại");
                    System.exit(0);
                    break;
            }

        } while (isRunning2);

    }
}
