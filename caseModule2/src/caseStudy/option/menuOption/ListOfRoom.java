package caseStudy.option.menuOption;

import caseStudy.option.clazz.Room;
import caseStudy.option.format.ReadWrite;

import java.util.ArrayList;
import java.util.List;

public class ListOfRoom {
    private static final String path = "data\\room.csv";
    private final int capacityRoom = 10;
//    private boolean[] numbRoom = new boolean[capacityRoom];




    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        List<String> lines = ReadWrite.read(path);
        for (String line : lines) {
            Room room = Room.parseRoom(line);
            rooms.add(room);
        }
        return rooms;
    }

    public boolean checkNumbRoom(int numbRoom) {
        List<Room> rooms = new ArrayList<>();
        if (rooms.size() >= capacityRoom) {
            System.out.println("Hết phòng không thể thêm");
            System.out.println("----------------------------------------------");
            return false;
        }
        return true;
    }

    public void add(Room room) {
        List<Room> rooms = findAll();
        rooms.add(room);
        ReadWrite.write(path , rooms);
        System.out.println("----------------------------------------------");
    }

}
