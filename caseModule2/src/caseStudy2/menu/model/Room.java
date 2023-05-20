package caseStudy2.menu.model;

import caseStudy2.menu.admin.roomService.listOfRoom.ListOfRoom;

import static caseStudy2.menu.format.ReadWrite.read;
import static caseStudy2.menu.format.ReadWrite.write;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private ListOfRoom listOfRoom = new ListOfRoom();
    private int numbRoom;
    private boolean status;
    private int numbPersonLive;
    private int numbWater;
    private int numbElectric;

    private int numbElectricUse;
    private int moneyRoom;

    public Room() {
    }

    public Room(int numbRoom, int numbPersonLive, int numbElectric, int numbWater, int numbElectricUse, int moneyRoom) {
        this.numbRoom = numbRoom;
        this.numbPersonLive = numbPersonLive;
        this.numbElectric = numbElectric;
        this.numbWater = numbWater;
        this.numbElectricUse = numbElectricUse;
        this.moneyRoom = moneyRoom;
    }

    public static Room parseRoom(String raw) {
        String regex = "^\\d+,\\d+,\\d+,\\d+,\\d+,\\d+,\\d+$";
        Room room = new Room();
        if (raw.matches(regex)) {
            String[] fields = raw.split(",");

            int numbRoom = Integer.parseInt(fields[0]);
            int numbPersonLive = Integer.parseInt(fields[1]);
            int numbElectric = Integer.parseInt(fields[2]);
            int numbWater = Integer.parseInt(fields[3]);
            int numbElectricUse = Integer.parseInt(fields[4]);
            int moneyRoom = Integer.parseInt(fields[5]);
            int billRoom = Integer.parseInt(fields[6]);


            room = new Room(numbRoom, numbPersonLive, numbElectric, numbWater, numbElectricUse, moneyRoom);
        }
        return room;
    }

    public int getNumbRoom() {
        return numbRoom;
    }

    public void setNumbRoom(int numbRoom) {
        this.numbRoom = numbRoom;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNumbPersonLive() {
        return numbPersonLive;
    }

    public void setNumbPersonLive(int numbPersonLive) {
        this.numbPersonLive = numbPersonLive;
    }

    public int getNumbWater() {
        return numbWater;
    }

    public void setNumbWater(int numbWater) {
        this.numbWater = numbWater;
    }

    public int getNumbElectric() {
        return numbElectric;
    }

    public void setNumbElectric(int numbElectric) {
        this.numbElectric = numbElectric;
    }

    public int getNumbElectricUse() {
        return numbElectricUse;
    }

    public void setNumbElectricUse(int numbElectricUse) {
        this.numbElectricUse = numbElectricUse;
    }

    public int getMoneyRoom() {
        return moneyRoom;
    }

    public void setMoneyRoom(int moneyRoom) {
        this.moneyRoom = moneyRoom;
    }
    public int getMoneyElectric(){
        return this.numbElectric * this.numbElectricUse;
    }
    public int getMoneyWater(){
        return this.numbWater * this.numbPersonLive;
    }


    public int getBillRoom() {
        return this.getMoneyElectric() + this.getMoneyWater() + this.moneyRoom;
    }

    public void delete(int numbRoom){
        List<String> lines = read("data\\room.csv");
        List<Room> rooms = new ArrayList<>();

        for (String line:lines) {
            Room room = Room.parseRoom(line);
            if (room.getNumbRoom() != numbRoom){
                rooms.add(room);
            }
        }
        write("data\\room.csv" , rooms);

        for (Room room: listOfRoom.findAll()) {
            if (room.getNumbRoom() == numbRoom){
                room.setStatus(false);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return this.getNumbRoom() +
                "," + this.getNumbPersonLive() +
                "," + this.getNumbElectric() +
                "," + this.getNumbWater() +
                "," + this.getNumbElectricUse() +
                "," + this.getMoneyRoom() +
                "," + this.getBillRoom() +
                "\n";
    }
}
