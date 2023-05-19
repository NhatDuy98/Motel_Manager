package caseStudy2.menu.format;

import static caseStudy2.Run.scanner;


public class RetryInput {
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
}
