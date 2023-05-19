package caseStudy2.menu.admin.customerService.addCustomer;


import caseStudy2.menu.admin.displayCustomer.DisplayCustomer;
import caseStudy2.menu.format.FormatForm;
import caseStudy2.menu.admin.customerService.listOfCustomer.ListOfCustomer;
import caseStudy2.menu.admin.listOfMotel.ListOfMotel;
import caseStudy2.menu.clazz.Customer;
import caseStudy2.menu.format.RetryInput;
import caseStudy2.menu.clazz.Motel;

import static caseStudy2.Run.scanner;


public class AddCustomer {
    private ListOfMotel listOfMotel = new ListOfMotel();
    private ListOfCustomer listOfCustomer = new ListOfCustomer();
    private DisplayCustomer display = new DisplayCustomer();
    public AddCustomer(){}
    private static String inputName() {
        return RetryInput.retryString("Tên");
    }

    private static String inputPhone() {
        do {
            try {
                String phone = RetryInput.retryString("SĐT");
                Integer.parseInt(phone);
                if (!FormatForm.checkPhoneNumber(phone)) {
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
                if (!FormatForm.checkAge(age)) {
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
    private static int inputNumbRoom(){
        do {
            try {
                String numbRoom = RetryInput.retryString("Số phòng");
                return Integer.parseInt(numbRoom);
//                return numbRoom;
            } catch (Exception exception) {
                System.out.println("Vui lòng nhập đúng định dạng số.");
                System.out.println("----------------------------------------------");
            }
        } while (true);
    }

    public void running(){
        display.running();
        System.out.println("Nhập thông tin người thuê");
        System.out.println("----------------------------------------------");
        int numbRoom = scanner.nextInt();
        boolean checkMotel = false;
        System.out.println("----------------------------------------------");

        for (Motel motel:listOfMotel.findAll()) {
            if (motel.getRoomNumb() == numbRoom){
                checkMotel = true;
                String name = inputName();
                String age = inputAge();
                String address = inputAddress();
                String phoneNumber = inputPhone();
                String dob = inputDob();
                String cccd = inputCCCD();


                Customer newCustomer = new Customer(name, age, address, phoneNumber, dob, cccd, numbRoom,0);
                listOfCustomer.add(newCustomer);

            }
        }
        if (!checkMotel){
            System.out.println("Số phòng từ 101 - 110");
            System.out.println("----------------------------------------------");
            return;
        }

        System.out.println("Bạn đã thêm thông tin thành công");
        System.out.println("----------------------------------------------");
        for (Motel motel : listOfMotel.findAll()) {
            if (motel.getRoomNumb() == numbRoom) {
                motel.setStatus(true);
            }
        }
        for (Customer customer:listOfCustomer.findAll()) {
            if (customer.getRoomNumb() == numbRoom){
                customer.setStatus(true);
            }
        }
    }
}
