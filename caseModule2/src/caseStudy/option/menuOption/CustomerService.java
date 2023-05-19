package caseStudy.option.menuOption;

import caseStudy.option.clazz.Customer;
import caseStudy.option.clazz.Room;
import caseStudy.option.format.Format;
import caseStudy.option.format.RetryInput;

import java.io.File;
import java.util.*;

import static caseStudy.option.format.ReadWrite.write;
import static caseStudy.option.format.ReadWrite.read;
import static caseStudy.option.menuOption.Display.display;

public class CustomerService {
    public static Scanner scanner = new Scanner(System.in);
    public static ListOfRoom listOfRoom = new ListOfRoom();
    public static ListOfCustomer listOfCustomer = new ListOfCustomer();

    public static void customerService() {
        int choice2;
        do {
            System.out.println("Quản lý người thuê");
            System.out.printf("%d. Thêm thông tin người thuê \t\t\t %d. Sửa thông tin người thuê \t\t\t %d. Back \t\t\t %d. Exit \n", 1, 2, 9, 0);
            System.out.print("Enter your choice: ");
            choice2 = scanner.nextInt();
            switch (choice2) {
                case 1:
                    addCustomer();
                    break;
                case 2:
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
                case 9:
                    choice2 = -1;
                    break;
                case 0:
                    System.out.println("Hẹn gặp lại");
                    System.exit(0);
                    break;
            }
        } while (choice2 != -1);
    }

    private static String inputName() {
        return RetryInput.retryString("Tên");
    }

    private static String inputPhone() {
        do {
            try {
                String phone = RetryInput.retryString("SĐT");
                Integer.parseInt(phone);
                if (!Format.checkPhoneNumber(phone)) {
                    System.out.println("SĐT bắt đầu bằng 0 và có 10 số.");
                    System.out.println("----------------------------------------------");
                    continue;
                }
                return phone;

            } catch (Exception exception) {
                System.out.println("Vui lòng nhập dúng định dạng số.");
                System.out.println("----------------------------------------------");
            }
        } while (true);
    }

    private static String inputAge() {
        do {
            try {
                String age = RetryInput.retryString("Tuổi");
                Integer.parseInt(age);
                if (!Format.checkAge(age)) {
                    System.out.println("Tuổi phải lớn hơn 0 và nhỏ hơn 100.");
                    System.out.println("----------------------------------------------");
                    continue;
                }
                return age;

            } catch (Exception exception) {
                System.out.println("Vui lòng nhập đúng định dạng số.");
                System.out.println("----------------------------------------------");
            }
        } while (true);
    }

    private static String inputAddress() {
        return RetryInput.retryString("Địa chỉ");
    }

    private static String inputDob() {
        return RetryInput.retryString("Ngày sinh");
    }

    private static String inputCCCD() {
        do {
            try {
                String numbCCCD = RetryInput.retryString("CCCD");
                Integer.parseInt(numbCCCD);
                return numbCCCD;
            } catch (Exception exception) {
                System.out.println("Vui lòng nhập đúng định dạng số.");
                System.out.println("----------------------------------------------");
            }
        } while (true);
    }
    private static String inputNumbRoom(){
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

    private static void addCustomer() {
        display();
        System.out.println("Nhập thông tin người thuê");
        System.out.println("----------------------------------------------");
        int numbRoom = Integer.parseInt(inputNumbRoom());
        System.out.println("----------------------------------------------");

        String name = inputName();
        String age = inputAge();
        String address = inputAddress();
        String phoneNumber = inputPhone();
        String dob = inputDob();
        String cccd = inputCCCD();


        Customer newCustomer = new Customer(name, age, address, phoneNumber, dob, cccd, numbRoom);
        listOfCustomer.add(newCustomer);
        System.out.println("Bạn đã thêm thông tin thành công");
        System.out.println("----------------------------------------------");
        for (Room room : listOfRoom.findAll()) {
            if (room.getRoomNumb() == numbRoom) {
                room.setStatus(true);
            }
        }
    }

    private static void removeCustomer() {
        System.out.println("Xóa thông tin");
        System.out.println("----------------------------------------------");
        display();
        System.out.print("Nhập số phòng bạn muốn xóa thông tin: ");
        int numbRoom = scanner.nextInt();

        List<Customer> customers = listOfCustomer.findAll();
        List<Room> rooms = listOfRoom.findAll();

        boolean foundRoom = false;
        boolean foundCustomer = false;

        for (Room room : rooms) {
            if (room.getRoomNumb() == numbRoom) {
                foundRoom = true;
                if (!room.isStatus()) {
                    for (Customer customer : customers) {
                        if (customer.getRoomNumb() == numbRoom) {
                            foundCustomer = true;
                            customer.deleteInfo(numbRoom);
                            System.out.println("Bạn đã xóa thành công");
                            System.out.println("----------------------------------------------");
                            break;
                        }
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
        display();
        System.out.print("Nhập số phòng bạn muốn sửa: ");
        int roomNumb = scanner.nextInt();
        List<Customer> customers = listOfCustomer.findAll();
        List<Room> rooms = listOfRoom.findAll();

        boolean foundRoom = false;
        boolean foundCustomer = false;

        for (Room room : rooms) {
            if (room.getRoomNumb() == roomNumb) {
                foundRoom = true;
                if (!room.isStatus()) {
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
                            File file = new File("data\\customer.csv");
                            file.delete();
                            File fileTemp = new File("data\\customer_temp.csv");
                            fileTemp.renameTo(file);
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
