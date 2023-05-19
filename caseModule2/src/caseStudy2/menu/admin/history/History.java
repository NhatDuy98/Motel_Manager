package caseStudy2.menu.admin.history;

import caseStudy2.menu.format.ReadWrite;

public class History {
    private ListOfHistory listOfHistory = new ListOfHistory();
    public History(){}
    public void running(){
        ReadWrite.read("data\\history.csv");

        System.out.println("LỊCH SỬ GIAO DỊCH");
        System.out.println("----------------------------------------------");
        System.out.printf("%-15s %-20s %-20s %-20s \n","Phòng","Hình thức","Số tiền","Ngày giờ ");
        for (String history: listOfHistory.findAll()) {
            String[] historis = history.split(",");
            System.out.printf(" %-15s %-20s %-12s %-5s \n",
                    historis[0],
                    historis[2],
                    historis[1],
                    historis[3]);
        }
        System.out.println("----------------------------------------------");
    }
}
