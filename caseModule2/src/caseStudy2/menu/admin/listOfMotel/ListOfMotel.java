package caseStudy2.menu.admin.listOfMotel;

import caseStudy2.menu.clazz.Motel;
import caseStudy2.menu.format.ReadWrite;

import java.util.ArrayList;
import java.util.List;

public class ListOfMotel {
    private static final String path = "data\\motel.csv";
    private final int capacityRoom = 10;

    public List<Motel> findAll() {
        List<Motel> motels = new ArrayList<>();
        List<String> lines = ReadWrite.read(path);
        for (String line : lines) {
            Motel motel = Motel.parseRoom(line);
            motels.add(motel);
        }
        return motels;
    }

    public boolean checkNumbRoom(int numbRoom) {
        List<Motel> motels = new ArrayList<>();
        if (motels.size() >= capacityRoom) {
            System.out.println("Hết phòng không thể thêm");
            System.out.println("----------------------------------------------");
            return false;
        }
        return true;
    }

    public void add(Motel motel) {
        List<Motel> motels = findAll();
        motels.add(motel);
        ReadWrite.write(path , motels);
        System.out.println("----------------------------------------------");
    }
}
