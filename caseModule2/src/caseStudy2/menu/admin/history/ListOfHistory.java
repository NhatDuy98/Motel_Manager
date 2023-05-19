package caseStudy2.menu.admin.history;

import caseStudy2.menu.format.ReadWrite;

import java.util.ArrayList;
import java.util.List;

public class ListOfHistory {
    private final String path = "data\\history.csv";

    public List<String> findAll(){
        List<String> historis = new ArrayList<>();
        List<String> lines = ReadWrite.read(path);
        for (String line:lines) {
            String history = parseHistory(line);
            historis.add(history);
        }
        return historis;
    }

    public void add(String str){
        List<String> historis = findAll();
        historis.add(str);
        ReadWrite.write(path , historis);
    }

    public static String parseHistory(String raw){
        String[] fields = raw.split(",");

        int numbRoom = Integer.parseInt(fields[0]);
        int money = Integer.parseInt(fields[1]);
        String form = fields[2];
        String time = fields[3];

        return  numbRoom + "," + money + ","  + form  + "," + time + "\n";
    }
}
