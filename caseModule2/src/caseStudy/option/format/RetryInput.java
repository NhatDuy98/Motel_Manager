package caseStudy.option.format;

import java.util.Scanner;

public class RetryInput {
    static Scanner scanner = new Scanner(System.in);

    public static String retryString(String info){
        String result;
        System.out.printf("Vui lòng nhập %s: ",info);
        while ((result = scanner.nextLine()).isEmpty()){
            System.out.printf("%s không được để trống. \n" , info);
            System.out.println("----------------------------------------------");
            System.out.printf("Vui lòng nhập %s: " , info);
        }
        return result;
    }
//    public static int retryParseInt(int number){
//        int result;
//        do {
//            try {
//                result = Integer.parseInt(scanner.nextLine());
//                return result;
//            } catch (Exception exception){
//                System.out.println("Vui lòng nhập đúng định dạng số.");
//            }
//        } while (true);
//    }

}
