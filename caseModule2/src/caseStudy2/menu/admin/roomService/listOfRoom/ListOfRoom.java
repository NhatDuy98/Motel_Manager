package caseStudy2.menu.admin.roomService.listOfRoom;

import caseStudy2.menu.model.Room;
import caseStudy2.menu.format.ReadWrite;

import java.util.ArrayList;
import java.util.List;

public class ListOfRoom {

    private static final String path = "data\\room.csv";

    public List<Room> findAll(){
        List<Room> rooms = new ArrayList<>();
        List<String> lines = ReadWrite.read(path);
        for (String line:lines) {
            Room room = Room.parseRoom(line);
            rooms.add(room);
        }
        return rooms;
    }

    public void add(Room room){
        List<Room> rooms = findAll();
        rooms.add(room);
        ReadWrite.write(path, rooms);
    }
}
