package pl.coderslab.app;

import pl.coderslab.Models.DbManager;
import pl.coderslab.Models.User;
import java.sql.Connection;
import java.util.Scanner;

public class controllerUsers {
    public static void main(Scanner scanner) {
        DbManager db = DbManager.getInstance();
        Connection conn = db.getConnection();
        System.out.println("   #############   Programming School Users administration panel   #############   ");
        while (true) {
            System.out.println("Current students: ===================================================================");
            User[] currentUsers = User.loadAllUsers(conn);
            for (User u : currentUsers) {
                System.out.println(u);
            }
            System.out.println("End of current students: ============================================================");

            System.out.println("Available options (type word): " +
                    "\n\t 'add' -add new student, 'edit' -edit student, 'del' -delete student, 'quit' -exit panel");
//            Scanner scanner = new Scanner(System.in);
            String initAnswer = scanner.nextLine();

            if (initAnswer.equals("add")) {
                System.out.println("Creating new user: ==============================================================");
                User newUser = new User();
                System.out.print("Type user name: ");
                newUser.setUsername(scanner.nextLine());
                System.out.print("Type user email: ");
                newUser.setEmail(scanner.nextLine());
                while (true) {
                    System.out.print("Insert number of user group: ");
                    if (scanner.hasNextInt()) {
                        newUser.setPersonGroupId(scanner.nextInt());
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Wrong input. Try again.");
                        scanner.next();
                    }
                }
                System.out.print("Type user password: ");
                newUser.setPassword(scanner.nextLine());
                newUser.saveToDB(conn);
            } else if (initAnswer.equals("edit")) {
                System.out.println("Editing user with specific id: ==================================================");
                while (true) {
                    System.out.print("Type id of user to edit: ");
                    if (scanner.hasNextInt()) {
                        int idToEdit = scanner.nextInt();
                        scanner.nextLine();
                        User userToEdit = User.loadUserById(conn, idToEdit);
                        System.out.print("Type new username: ");
                        userToEdit.setUsername(scanner.nextLine());
                        System.out.print("Type new email: ");
                        userToEdit.setEmail(scanner.nextLine());
                        System.out.print("Type new password: ");
                        userToEdit.setPassword(scanner.nextLine());
                        System.out.print("Type new group: ");
                        userToEdit.setPersonGroupId(scanner.nextInt());
                        scanner.nextLine();
                        userToEdit.saveToDB(conn);
                        break;
                    } else {
                        System.out.println("Wrong input. Try again.");
                        scanner.next();
                    }
                }

            } else if (initAnswer.equals("del")) {
                System.out.println("Removing user with specific id: =================================================");
                while (true) {
                    System.out.print("Type id of user to remove: ");
                    if (scanner.hasNextInt()) {
                        int idToDelete = scanner.nextInt();
                        scanner.nextLine();
                        User userToDelete = User.loadUserById(conn, idToDelete);
                        userToDelete.delete(conn);
                        break;
                    } else {
                        System.out.println("Wrong input. Try again.");
                        scanner.next();
                    }
                }

            } else if (initAnswer.equals("quit")) {
                System.out.println("Exit program ====================================================================");
//                scanner.close();
                break;
            } else {
                System.out.println("Wrong input. Try again");
            }
        }
        System.out.println("Bye");
    }
}

