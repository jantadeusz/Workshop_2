package pl.coderslab.app;

import pl.coderslab.Models.DbManager;
import pl.coderslab.Models.Exercise;
import pl.coderslab.Models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class controllerExercises {
    public static void main(Scanner scanner) {
        DbManager db = DbManager.getInstance();
        Connection conn = null;
        conn = db.getConnection();
        System.out.println("   #############     Programming School Exercises administration panel   #############   ");
        while (true) {
            System.out.println("Current exercises: ==================================================================");
            Exercise[] currentExercises = Exercise.loadAll(conn);
            for (Exercise e : currentExercises) {
                System.out.println(e);
            }
            System.out.println("End of current exercises: ===========================================================");

            System.out.println("Available options (type word): " +
                    "\n\t'add' -add new exercise, 'edit' -edit exercise, 'del' -delete exercise, 'quit' -exit panel");
//            Scanner scanner = new Scanner(System.in);
            String initAnswer = scanner.nextLine();

            if (initAnswer.equals("add")) {
                System.out.println("Creating new exercise: ==========================================================");
                Exercise newExercise = new Exercise();
                System.out.print("Type exercie title: ");
                newExercise.setTitle(scanner.nextLine());
                System.out.print("Type exercise description: ");
                newExercise.setDescription(scanner.nextLine());
                newExercise.saveToDB(conn);
            } else if (initAnswer.equals("edit")) {
                System.out.println("Editing exercise with specific id: ==============================================");
                while (true) {
                    System.out.print("Type id of exercise to edit: ");
                    if (scanner.hasNextInt()) {
                        int idToEdit = scanner.nextInt();
                        scanner.nextLine();
                        Exercise exerciseToEdit = Exercise.loadById(conn, idToEdit);
                        System.out.print("Type new title: ");
                        exerciseToEdit.setTitle(scanner.nextLine());
                        System.out.print("Type new description: ");
                        exerciseToEdit.setDescription(scanner.nextLine());
                        exerciseToEdit.saveToDB(conn);
                        break;
                    } else {
                        System.out.println("Wrong input. Try again.");
                        scanner.next();
                    }
                }
            } else if (initAnswer.equals("del")) {
                System.out.println("Removing exercise with specific id: =============================================");
                while (true) {
                    System.out.print("Type id of exercise to remove: ");
                    if (scanner.hasNextInt()) {
                        int idToDelete = scanner.nextInt();
                        scanner.nextLine();
                        Exercise exerciseToDelete = Exercise.loadById(conn, idToDelete);
                        exerciseToDelete.delete(conn);
                        break;
                    } else {
                        System.out.println("Type number. Try again.");
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