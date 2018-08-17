package pl.coderslab.app;

import org.mindrot.BCrypt;
import pl.coderslab.Models.DbManager;
import pl.coderslab.Models.Solution;
import pl.coderslab.Models.User;

import java.sql.Connection;
import java.util.Scanner;

public class controllerStudent {

    public static void main(Scanner scan) {
        Connection conn = DbManager.getInstance().getConnection();
        System.out.println("Welcome in Student panel ");
        int id = -1;
        while (true) {
            System.out.print("Type your userName or 'quit' -exit main menu. ");
            String login = scan.nextLine(); //testuser
            if (login.equals("quit")) {
                break;
            }
            System.out.print("Type password to your account: ");
            String candidate = scan.nextLine(); // password: test
            User[] users = User.loadAllUsers(conn);
            id = User.getIdFromUserName(users, login);
            if (id == -1) {
                System.out.println("User name not found. Try again");
            } else {
                User loggedUser = User.loadUserById(conn, id);
                if (BCrypt.checkpw(candidate, loggedUser.getPassword())) {// sprawdzenie czy haslo jest poprawne
                    System.out.println("Welcome " + loggedUser.getUsername());
                    break;
                } else {
                    System.out.println("Wrong password");
                }
            }
        }
        // In my opinion task described in pdf is poor for user. Add - shows only solutions with
        // description == null. Problem occurs in case of correcting/updating existing !=nul solution.
        // I made this part of project in my own way.
        while (true) {
            System.out.println("Your actual solutions in database <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            Solution[] solutions = Solution.loadAllByUserId(conn, id);
            for (Solution s : solutions) {
                System.out.println(s);
            }
            System.out.println("\t\t\t\t\t\t\t\t\t\t>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> End of solutions"); // there should be a column in tab: solution named "rank' fulfilled by teacher
            int solutionId = -1;
            boolean exitFlag = false;
            while (true) {
                try {
                    System.out.print("Type id of solution you want to update or 'quit' to exit panel: ");
                    String decisionAfterSolutionShow = scan.nextLine();
                    if (decisionAfterSolutionShow.equals("quit")) {
                        exitFlag = true;
                        break;
                    }
                    solutionId = Integer.parseInt(decisionAfterSolutionShow);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong data. Try again.");
                }
            }
            if (exitFlag)
                break;
            Solution solutionToUpdate = Solution.loadById(conn, solutionId);
            System.out.print("Type new description of solution or 'quit' to exit panel: ");
            String newDescription = scan.nextLine();
            if (newDescription.equals("quit"))
                break;
            try {
                solutionToUpdate.setDescription(newDescription);
                solutionToUpdate.saveToDB(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

