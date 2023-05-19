package caseStudy2;

import caseStudy2.menu.Menu;
import java.util.Scanner;

public class Run {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.running();
    }
}
