package caseStudy2.menu.admin.roomService.displayRoom;

import caseStudy2.menu.admin.customerService.listOfCustomer.ListOfCustomer;
import caseStudy2.menu.admin.listOfMotel.ListOfMotel;
import caseStudy2.menu.admin.roomService.listOfRoom.ListOfRoom;
import caseStudy2.menu.model.Customer;
import caseStudy2.menu.model.Motel;
import caseStudy2.menu.model.Room;

public class DisplayRoom {
    private ListOfMotel listOfMotel = new ListOfMotel();
    private ListOfCustomer listOfCustomer = new ListOfCustomer();
    private ListOfRoom listOfRoom = new ListOfRoom();

    public DisplayRoom() {
    }

    public void running() {
        System.out.println("DANH SÁCH THÔNG TIN PHÒNG");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-20s %-15s %-15s\n",
                "Phòng",
                "Số người",
                "Số điện đã dùng",
                "Tổng bill",
                "Tiền chưa thanh toán");

        for (Motel motel:listOfMotel.findAll()) {
            for (Customer customer:listOfCustomer.findAll()) {
                if (customer.getRoomNumb() == motel.getRoomNumb()){
                    for (Room room:listOfRoom.findAll()) {
                        if (room.getNumbRoom() == customer.getRoomNumb()){
                            System.out.printf("%-15s %-15s %-20s %-15s %-15s\n",
                                    motel.getRoomNumb(),
                                    room.getNumbPersonLive(),
                                    room.getNumbElectricUse(),
                                    room.getBillRoom(),
                                    customer.getTemp());
                            motel.setStatus(true);
                        }
                    }
                }
            }
            if (!motel.isStatus()) {
                System.out.println(motel.getRoomNumb() + ": Trống");
            }
        }

//        for (Motel motel : listOfMotel.findAll()) {
//            boolean hasCustomer = false;
//            for (Customer customer : listOfCustomer.findAll()) {
//                if (customer.getRoomNumb() == motel.getRoomNumb() && !customer.isStatus()) {
//                    System.out.println(motel.getRoomNumb() + ": Chưa thêm thông tin");
//                    hasCustomer = true;
//                    break;
//                }
//                for (Room room : listOfRoom.findAll()) {
//                    if (motel.getRoomNumb() == room.getNumbRoom()) {
//                        System.out.printf("%-15s %-15s %-20s %-15s %-15s\n",
//                                motel.getRoomNumb(),
//                                room.getNumbPersonLive(),
//                                room.getNumbElectricUse(),
//                                room.getBillRoom(),
//                                customer.getTemp());
//                        motel.setStatus(true);
//                    }
//                }
//            }
//            if (!motel.isStatus() && !hasCustomer) {
//                System.out.println(motel.getRoomNumb() + ": Trống");
//            }
//        }
    }
}
