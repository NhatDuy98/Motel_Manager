package caseStudy2.menu;

import caseStudy2.menu.admin.Admin;
import caseStudy2.menu.user.User;
import static caseStudy2.Run.scanner;

public class Menu {
    public Menu(){}

    public void running(){
        System.out.println("PHÒNG TRỌ LOVE");
        do {
            System.out.println("==================");
            System.out.print("Tên đăng nhập: ");
            String account = scanner.next();
            System.out.print("Mật khẩu: ");
            String password = scanner.next();
            System.out.println("==================");
            if (account.equals("admin") && password.equals("123")){
                Admin admin = new Admin();
                admin.running();
            } else if (account.equals("user") && password.equals("123")) {
                User user = new User();
                user.running();
            } else {
                System.out.println("Vui lòng nhập đúng thông tin");
            }
        }while (true);
    }
}
