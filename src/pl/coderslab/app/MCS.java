package pl.coderslab.app;

import java.util.Scanner;

// Main execution file for Coderslab Workshop with Active record pattern
public class MCS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1-Teacher, 2-User, 3-Group edit");
        String decide = scanner.nextLine();
        switch (decide) {
            case "1":
                controllerTeacher.main();
            case "3":
                controllerGroup.main();
        }




        System.out.println("end of School");
    }
}
