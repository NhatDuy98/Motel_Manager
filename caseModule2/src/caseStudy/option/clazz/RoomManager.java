package caseStudy.option.clazz;

import java.util.List;

import static caseStudy.option.menuOption.CustomerService.listOfRoom;

public class RoomManager {
    private int roomNumb;
    private int numbWaterUse;
    private int numbElectricLast;
    private int numbElectricNow;
    private int numbMoneyRoom;
    private int newBillRoom;
    private int totalBill;
    private int moneyDebt; // tiền còn lại
    private int numbPersonLive;
    private int moneyTemp = 0;
    private boolean status = false;

    public RoomManager() {
    }

    public RoomManager(int roomNumb, int numbPersonLive, int numbElectricLast, int numbElectricNow, int numbMoneyRoom) {
        this.roomNumb = roomNumb;
        this.numbPersonLive = numbPersonLive;
        this.numbWaterUse = numbPersonLive * 50000;
        this.numbElectricNow = numbElectricNow;
        this.numbElectricLast = numbElectricLast;
        this.numbMoneyRoom = numbMoneyRoom;
        this.newBillRoom = (this.numbElectricNow - this.numbElectricLast) * 3000 + this.numbWaterUse + this.numbMoneyRoom;
//        this.moneyTemp = 0;
//        this.moneyTemp = this.;
//        this.totalBill = (this.getNumbElectricNow() - this.getNumbElectricLast()) * 3000 + this.getNumbWaterUse() + this.getNumbMoneyRoom() + this.getMoneyDebt();

    }

    public static RoomManager parseRoomManager(String raw) {
        String regex = "^\\d+,\\d+,\\d+,\\d+,\\d+,\\d+,\\d+,\\d+$";
        RoomManager roomManager = new RoomManager();
        if (raw.matches(regex)) {
            String[] fields = raw.split(",");

            int roomNumb = Integer.parseInt(fields[0]);
            int numbPersonLive = Integer.parseInt(fields[1]);
            int numbElectricLast = Integer.parseInt(fields[2]);
            int numbElectricNow = Integer.parseInt(fields[3]);
            int numbWaterUse = Integer.parseInt(fields[4]);
            int numbMoneyRoom = Integer.parseInt(fields[5]);
            int newBillRoom = Integer.parseInt(fields[6]);
//            int totalBill = Integer.parseInt(fields[7]);
            int moneyTemp = Integer.parseInt(fields[7]);


            roomManager = new RoomManager(roomNumb, numbPersonLive, numbElectricLast, numbElectricNow, numbMoneyRoom);
        }
        return roomManager;
    }


    public int getRoomNumb() {
        return this.roomNumb;
    }

    public void setRoomNumb(int roomNumb) {
        this.roomNumb = roomNumb;
    }

    public int getNumbWaterUse() {
        return this.numbWaterUse;
    }

    public void setNumbWaterUse(int numbWaterUse) {
        this.numbWaterUse = numbWaterUse;
    }

    public int getNumbElectricLast() {
        return this.numbElectricLast;
    }

    public int getNumbElectricNow() {
        return this.numbElectricNow;
    }

    public int setNumbElectricLast(int numbElectricLast) {
        return this.numbElectricLast = numbElectricLast;
    }

    public int setNumbElectricNow(int numbElectricNow) {
        return this.numbElectricNow = numbElectricNow;
    }

    public int getNumbMoneyRoom() {
        return this.numbMoneyRoom;
    }

    public void setNumbMoneyRoom(int numbMoneyRoom) {
        this.numbMoneyRoom = numbMoneyRoom;
    }

    public int getNewBillRoom() {
        return newBillRoom = (this.getNumbElectricNow() - this.getNumbElectricLast()) * 3000 + this.getNumbWaterUse() + this.getNumbMoneyRoom() + this.getMoneyDebt();
    }
    public void setNewBillRoom(int newBillRoom){
        this.newBillRoom = newBillRoom;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void moneyDebt(int money) {
        if (money > this.newBillRoom) {
            System.out.println("Số tiền lớn hơn tiền cần thu");
        }
        int result = this.getNewBillRoom() - money;
    }
    public int getMoneyDebt() {
        return moneyDebt;
    }

    public void setMoneyDebt(int money) {
        int result = this.getNewBillRoom() - money;
    }
    public int getTotalBill() {
        return totalBill = (this.getNumbElectricNow() - this.getNumbElectricLast()) * 3000 + this.getNumbWaterUse() + this.getNumbMoneyRoom() + this.getMoneyDebt();
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }
    public int getMoneyTemp() {
//        int newBill = this.getNewBillRoom();
//        if (newBill == 0){
//            return moneyTemp = this.getNewBillRoom() + this.getTotalBill() - this.getMoneyDebt();
//            return moneyTemp;
//        }
//        int totalBill = this.getTotalBill();
//        int moneyDebt = this.getMoneyDebt();
//        return moneyTemp = newBill + totalBill  - moneyDebt;
        return this.moneyTemp;
    }

    public void setMoneyTemp(int moneyTemp) {
        this.moneyTemp = moneyTemp;
    }

    public int getNumbPersonLive() {
        return numbPersonLive;
    }

    public void setNumbPersonLive(int numbPersonLive) {
        this.numbPersonLive = numbPersonLive;
    }

    @Override
    public String toString() {
        List<Room> rooms = listOfRoom.findAll();
        String result = "";
        for (Room room : rooms) {
            if (!room.isStatus()) {
                result = this.getRoomNumb() +
                        "," + this.getNumbPersonLive() +
                        "," + this.getNumbElectricLast() +
                        "," + this.getNumbElectricNow() +
                        "," + this.getNumbWaterUse() +
                        "," + this.getNumbMoneyRoom() +
                        "," + this.getNewBillRoom() +
                        "," + this.getMoneyTemp() +
                        "\n";
//                "," + this.getTotalBill() +
                break;
            }
        }
            return result;
//        return this.getRoomNumb() +
//                "," + this.getNumbPersonLive() +
//                "," + this.getNumbElectricLast() +
//                "," + this.getNumbElectricNow() +
//                "," + this.getNumbWaterUse() +
//                "," + this.getNumbMoneyRoom() +
//                "," + this.getBillRoom() +
//                "\n";
        }
}
