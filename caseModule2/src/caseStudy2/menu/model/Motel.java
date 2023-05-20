package caseStudy2.menu.model;


public class Motel {
    private int roomNumb;
    private boolean status = false;

    public Motel() {
    }

    public Motel(int roomNumb) {
        this.roomNumb = roomNumb;
    }


    public static Motel parseRoom(String raw) {
        String[] field = raw.split(",");

        int numbRoom = Integer.parseInt(field[0]);

        return new Motel(numbRoom);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Motel setRoomNumb(int roomNumb) {
        this.roomNumb = roomNumb;
        return this;
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
                "\n";
    }
}
