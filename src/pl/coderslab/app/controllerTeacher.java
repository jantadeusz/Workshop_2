package pl.coderslab.app;

import pl.coderslab.Models.DbManager;
import pl.coderslab.Models.Exercise;
import pl.coderslab.Models.Solution;
import pl.coderslab.Models.User;

import java.sql.Connection;
import java.util.Scanner;

public class controllerTeacher {
    public static void main(Scanner scanner) {
        DbManager db = DbManager.getInstance();
        Connection conn = db.getConnection();
        System.out.println("   #############   Programming School Teacher administration panel   #############   ");
        while (true) {
            System.out.println("Available options (type word): " +
                    "\n\t 'add' -assign new exercise to student, 'view' -view student's solutions, 'grade' -give grades to solutions, 'quit' -exit panel");
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
                System.out.println("Current exercises in database <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                Exercise[] exercises = Exercise.loadAll(conn);
                for (Exercise e : exercises) {
                    System.out.println(e);
                }
                System.out.println("End of current exercises in database >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
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
            } else if (initAnswer.equals("view")) {
                System.out.println("View solutions of specific student ==============================================");
                while (true) {
                    User[] users = User.loadAllUsers(conn);
                    for (User u : users) {
                        System.out.println(u);
                    }
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


            } else if (initAnswer.equals("grade")) {
                Solution[] solutions = Solution.loadSolvedWithoutGrade(conn);
                for (Solution s : solutions) {
                    System.out.println(s);
                }
                System.out.print("Type id of solution to give grade and comment: ");
                if (scanner.hasNextInt()) {
                    int idToGrade = scanner.nextInt();
                    scanner.nextLine();
                    Solution solutionToGrade = Solution.loadById(conn, idToGrade);
                    solutionToGrade = giveGradeValueForSolution(solutionToGrade, scanner);
                    System.out.println("Insert grade comment for this solution: ");
                    String inputForGradeComment = scanner.nextLine();
                    solutionToGrade.setGradeComment(inputForGradeComment);
                    solutionToGrade.saveGradeToDB(conn);
                } else {
                    System.out.println("Wrong input. Try again.");
                    scanner.next();
                }
            } else if (initAnswer.equals("quit")) {
                System.out.println("Exit program ====================================================================");
                break;
            } else {
                System.out.println("Wrong input. Try again");
            }
        }
    }

    public static Solution giveGradeValueForSolution(Solution solutionToGrade, Scanner scanner) {
        while (true) {
            System.out.println("Insert grade between 1-6 for this solution: ");
            if (scanner.hasNextInt()) {
                int inputForGradeValue = scanner.nextInt();
                scanner.nextLine();
                if (inputForGradeValue >= 1 && inputForGradeValue <= 6) {
                    solutionToGrade.setGradeVal(inputForGradeValue);
                    break;
                }
                System.out.println("Wrong input. Try again.");
            } else {
                System.out.println("Wrong input. Try again.");
                scanner.next();
            }
        }
        return solutionToGrade;
    }

}


