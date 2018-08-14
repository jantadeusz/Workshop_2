package pl.coderslab.app;

import pl.coderslab.Models.DbManager;
import pl.coderslab.Models.Exercise;
import pl.coderslab.Models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class controllerExercises {
    public static void main(String[] args) {
        DbManager db = DbManager.getInstance();
        Connection conn = null;
        try {
            conn = db.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("   #############     Programming School Exercises administration panel   #############   ");
        while (true) {
            System.out.println("Current exercises: ==================================================================");
            try {
                Exercise[] currentExercises = Exercise.loadAll(conn);
                for (Exercise e : currentExercises) {
                    System.out.println(e);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("End of current exercises: ===========================================================");

            System.out.println("Available options (type word): " +
                    "\n\t 'add' -add new exercise, 'edit' -edit exercise, 'del' -delete exercise, 'quit' -exit program");
            Scanner scanner = new Scanner(System.in);
            String initAnswer = scanner.nextLine();


            if (initAnswer.equals("add")) {
                System.out.println("Creating new exercise: ==========================================================");
                Exercise newExercise = new Exercise();
                System.out.print("Type exercie title: ");
                newExercise.setTitle(scanner.nextLine());
                System.out.print("Type exercise description: ");
                newExercise.setDescription(scanner.nextLine());
                try {
                    newExercise.saveToDB(conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (initAnswer.equals("edit")) {
                System.out.println("Editing exercise with specific id: ==============================================");
//                while (true) {
//                    System.out.print("Type id of user to edit: ");
//                    if (scanner.hasNextInt()) {
//                        int idToEdit = scanner.nextInt();
//                        scanner.nextLine();
//                        try {
//                            User userToEdit = User.loadUserById(conn, idToEdit);
//                            System.out.print("Type new username: ");
//                            userToEdit.setUsername(scanner.nextLine());
//                            System.out.print("Type new email: ");
//                            userToEdit.setEmail(scanner.nextLine());
//                            System.out.print("Type new password: ");
//                            userToEdit.setPassword(scanner.nextLine());
//                            System.out.print("Type new group: ");
//                            userToEdit.setPersonGroupId(scanner.nextInt());
//                            scanner.nextLine();
//                            userToEdit.saveToDB(conn);
//                            break;
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        System.out.println("Type number. Try again.");
//                        scanner.next();
//                    }
//                }

            } else if (initAnswer.equals("del")) {
                System.out.println("Removing exercise with specific id: =============================================");
//                while (true) {
//                    System.out.print("Type id of user to remove: ");
//                    if (scanner.hasNextInt()) {
//                        int idToDelete = scanner.nextInt();
//                        scanner.nextLine();
//                        try {
//                            User userToDelete = User.loadUserById(conn, idToDelete);
//                            userToDelete.delete(conn);
//                            break;
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        System.out.println("Type number. Try again.");
//                        scanner.next();
//                    }
//                }

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