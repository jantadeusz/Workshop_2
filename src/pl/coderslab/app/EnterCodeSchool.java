package pl.coderslab.app;

import java.util.Scanner;

// Main execution file for Coderslab Workshop with Active record pattern
public class EnterCodeSchool {
    public static void main(String[] args) {
        while (true) {
            System.out.println("   #############   Main Code School menu (EnterCodeSchool)   #############   ");
            System.out.println("Available panels (type number to choose option): " +
                    "\n\t'1' -student panel, '2' -teacher panel, '3'-edit users, '4'-edit exercises, '5'-edit groups, '6' -exit program");
            Scanner scanner = new Scanner(System.in);
            String decide = scanner.nextLine();
            if (decide.equals("1")) {
                controllerStudent.main(scanner);
            } else if (decide.equals("2")) {
                controllerTeacher.main(scanner);
            } else if (decide.equals("3")) {
                controllerUsers.main(scanner);
            } else if (decide.equals("4")) {
                controllerExercises.main(scanner);
            } else if (decide.equals("5")) {
                controllerGroup.main(scanner);
            } else if (decide.equals("6")) {
                System.out.println("Exiting Code School");
                scanner.close();
                break;
            }
        }
    }
}

