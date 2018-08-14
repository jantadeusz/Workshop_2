package pl.coderslab.app;

import pl.coderslab.Models.DbManager;
import pl.coderslab.Models.Solution;
import pl.coderslab.Models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class controllerTeacher {
    public static void main() {
        DbManager db = DbManager.getInstance();
        Connection conn = db.getConnection();
        System.out.println("   #############   Programming School Teacher administration panel   #############   ");
        while (true) {
            System.out.println("Available options (type word): " +
                    "\n\t 'add' -assign new exercise to student, 'view' -view student's solutions, 'quit' -exit panel");
            Scanner scanner = new Scanner(System.in);
            String initAnswer = scanner.nextLine();
            if (initAnswer.equals("add")) {
                System.out.println("Assigning new exercise to student's profile: ====================================");
                Solution zeroStateSolution = new Solution();
                User[] users = User.loadAllUsers(conn);
                for (User u : users) {
                    System.out.println(u);
                }
                while (true) {
                    System.out.print("Type id of student to assign new exercise: ");
                    if (scanner.hasNextInt()) {
                        int idToAssign = scanner.nextInt();
                        scanner.nextLine();
                        zeroStateSolution.setUserId(idToAssign);
                        break;
                    } else {
                        System.out.println("Wrong input. Try again.");
                        scanner.next();
                    }
                }
                while (true) {
                    System.out.print("Type id of exercise to assign: ");
                    if (scanner.hasNextInt()) {
                        int exToAssign = scanner.nextInt();
                        scanner.nextLine();
                        zeroStateSolution.setExerciseId(exToAssign);
                        break;
                    } else {
                        System.out.println("Wrong input. Try again.");
                        scanner.next();
                    }
                }
                zeroStateSolution.saveToDB(conn);
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////dokoncz ze slajdu
            } else if (initAnswer.equals("view")) {
                System.out.println("View solutions of specific student ==============================================");
                while (true) {
                    System.out.print("Type id of student to view solutions: ");
                    if (scanner.hasNextInt()) {
                        int idToView = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Current solutions of student with id: " + idToView + " <<<<<<<<<<<<<<");
                        Solution[] solutions = Solution.loadAllByUserId(conn, idToView);
                        for (Solution s : solutions) {
                            System.out.println(s);
                        }
                        System.out.println("End of solutions. >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        break;
                    } else {
                        System.out.println("Wrong input. Try again.");
                        scanner.next();
                    }
                }
            } else if (initAnswer.equals("quit")) {
                System.out.println("Exit program ====================================================================");
                scanner.close();
                break;
            } else {
                System.out.println("Wrong input. Try again");
            }
        }
        System.out.println("Bye");
    }
}


