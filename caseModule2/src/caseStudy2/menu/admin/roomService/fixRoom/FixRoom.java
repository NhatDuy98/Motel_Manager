package caseStudy2.menu.admin.roomService.fixRoom;

import caseStudy2.menu.admin.listOfMotel.ListOfMotel;
import caseStudy2.menu.admin.roomService.listOfRoom.ListOfRoom;
import caseStudy2.menu.model.Motel;
import caseStudy2.menu.model.Room;

import java.util.ArrayList;
import java.util.List;

import static caseStudy2.Run.scanner;
import static caseStudy2.menu.format.ReadWrite.read;
import static caseStudy2.menu.format.ReadWrite.write;

public class FixRoom {
    private ListOfRoom listOfRoom = new ListOfRoom();
    private ListOfMotel listOfMotel = new ListOfMotel();
    public FixRoom(){}
    public void running(){
        System.out.println("SỬA THÔNG TIN PHÒNG");
        System.out.println("Nhâp phòng bạn muốn sửa");
        int numbRoom = scanner.nextInt();


        for (Motel motel:listOfMotel.findAll()) {
            if (motel.getRoomNumb() == numbRoom && !motel.isStatus()){
                for (Room room:listOfRoom.findAll()) {
                    if (room.getNumbRoom() == numbRoom){
                        System.out.print("Nhập giá điện mới: ");
                        int newElectric = scanner.nextInt();
                        System.out.print("Nhập giá nước mới: ");
                        int newWater = scanner.nextInt();
                        System.out.print("Nhập giá phòng mới: ");
                        int newMoneyRoom = scanner.nextInt();

                        List<String> lines = read("data\\room.csv");
                        List<Room> newRoom = new ArrayList<>();
                        for (String line:lines) {
                            Room newInfoRoom = Room.parseRoom(line);
                            if (newInfoRoom.getNumbRoom() == numbRoom){
                                newInfoRoom.setNumbElectric(newElectric);
                                newInfoRoom.setNumbWater(newWater);
                                newInfoRoom.setMoneyRoom(newMoneyRoom);
                            }
                            newRoom.add(newInfoRoom);
                        }
                        write("data\\room.csv" , newRoom);
                    }
                }
            }
        }
        System.out.println("Bạn đã sửa thông tin thành công");
        System.out.println("----------------------------------------------");
    }
}
