package caseStudy.option.clazz;

import java.util.List;

import static caseStudy.option.menuOption.CustomerService.listOfRoom;

public class Room {
    private int roomNumb;
    private Customer customer;
    private static int countRoom = 0;
    private boolean status = false;
    private int moneyTemp;
    private static List<Room> rooms = listOfRoom.findAll();

    public Room() {
    }

    public Room(int roomNumb) {
        this.roomNumb = roomNumb;
        Room.countRoom++;
    }

    public Room(int roomNumb, Customer customer) {
        this.roomNumb = roomNumb;
        this.customer = customer;
        Room.countRoom++;
    }

    public static Room parseRoom(String raw) {
//        String regex = "^\\d+,\\d+$";
//        Room room = new Room();
//        if (raw.matches(regex)){
//            String[] field = raw.split(",");
//
//            int numbRoom = Integer.parseInt(field[0]);
//
//            room = new Room(numbRoom);
//
//        }
//        return room;
        String[] field = raw.split(",");

        int numbRoom = Integer.parseInt(field[0]);

        return new Room(numbRoom);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static void setCountRoom(int countRoom) {
        Room.countRoom = countRoom;
    }

    public static int getCountRoom() {
        return countRoom;
    }

    public Room setRoomNumb(int roomNumb) {
        this.roomNumb = roomNumb;
        return this;
    }

    public int getMoneyTemp() {
        return moneyTemp;
    }

    public void setMoneyTemp(int moneyTemp) {
        this.moneyTemp = moneyTemp;
    }

    public int getRoomNumb() {
        return this.roomNumb;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return this.getRoomNumb() +
                "," + this.getMoneyTemp() +
                "\n";
//        String roomToString = "Số phòng: " + this.getRoomNumb() + "\n";
//        if (this.customer != null) {
//            roomToString += this.customer.toString();
//        }
//        return roomToString;
    }
}

