package caseStudy2.menu.admin.displayCustomer;

import caseStudy2.menu.admin.customerService.listOfCustomer.ListOfCustomer;
import caseStudy2.menu.admin.listOfMotel.ListOfMotel;
import caseStudy2.menu.clazz.Customer;
import caseStudy2.menu.clazz.Motel;

public class DisplayCustomer {
    private ListOfMotel listOfMotel = new ListOfMotel();
    private ListOfCustomer listOfCustomer = new ListOfCustomer();

    public DisplayCustomer() {
    }

    public void running() {
        System.out.println("Danh sách khách thuê phòng: ");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                "Phòng",
                "Tên",
                "Tuổi",
                "Địa chỉ",
                "SĐT",
                "Ngày sinh",
                "CCCD",
                "Chưa thanh toán");

        for (Motel motel : listOfMotel.findAll()) {
            for (Customer customer : listOfCustomer.findAll()) {
                if (customer.getRoomNumb() == motel.getRoomNumb()) {
                    System.out.printf("%-15s: %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                            motel.getRoomNumb(),
                            customer.getName(),
                            customer.getAge(),
                            customer.getAddress(),
                            customer.getPhoneNumber(),
                            customer.getDob(),
                            customer.getCccd(),
                            customer.getTemp());
                    motel.setStatus(true);
                }
            }
            if (!motel.isStatus()) {
                System.out.println(motel.getRoomNumb() + ": Trống");
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }
}
