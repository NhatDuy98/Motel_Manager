package caseStudy.option.menuOption;

import caseStudy.option.clazz.RoomManager;
import caseStudy.option.format.ReadWrite;

import java.util.ArrayList;
import java.util.List;

public class ListOfMoney {
    private static final String path = "data\\roomManager.csv";

    public List<RoomManager> findAll(){
        List<RoomManager> roomManagers = new ArrayList<>();
        List<String> lines = ReadWrite.read(path);
        for (String line:lines) {
            RoomManager roomManager = RoomManager.parseRoomManager(line);
            roomManagers.add(roomManager);
        }
        return roomManagers;
    }

    public void add(RoomManager roomManager){
        List<RoomManager> roomManagers = findAll();
        roomManagers.add(roomManager);
        ReadWrite.write(path , roomManagers);
    }
}
