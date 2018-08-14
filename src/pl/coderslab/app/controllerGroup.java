package pl.coderslab.app;

import pl.coderslab.Models.DbManager;
import pl.coderslab.Models.Group;
import java.sql.Connection;
import java.util.Scanner;

public class controllerGroup {
    public static void main() {
        DbManager db = DbManager.getInstance();
        Connection conn = null;
        conn = db.getConnection();
        System.out.println("   #############   Programming School Groups administration panel   #############   ");
        while (true) {
            System.out.println("Current groups: =====================================================================");
            Group[] currentGroups = Group.loadAll(conn);
            for (Group u : currentGroups) {
                System.out.println(u);
            }
            System.out.println("End of current groups: ==============================================================");

            System.out.println("Available options (type word): " +
                    "\n\t 'add' -add new group, 'edit' -edit group, 'del' -delete group, 'quit' -exit panel");
            Scanner scanner = new Scanner(System.in);
            String initAnswer = scanner.nextLine();

            if (initAnswer.equals("add")) {
                System.out.println("Creating new group: =============================================================");
                Group newGroup = new Group();
                System.out.print("Type group name: ");
                newGroup.setName(scanner.nextLine());
                newGroup.saveToDB(conn);
            } else if (initAnswer.equals("edit")) {
                System.out.println("Editing group with specific id: =================================================");
                while (true) {
                    System.out.print("Type id of group to edit: ");
                    if (scanner.hasNextInt()) {
                        int idToEdit = scanner.nextInt();
                        scanner.nextLine();
                        Group groupToEdit = Group.loadById(conn, idToEdit);
                        System.out.print("Type new name: ");
                        groupToEdit.setName(scanner.nextLine());
                        groupToEdit.saveToDB(conn);
                        break;
                    } else {
                        System.out.println("Wrong input. Try again.");
                        scanner.next();
                    }
                }

            } else if (initAnswer.equals("del")) {
                System.out.println("Removing group with specific id: ================================================");
                while (true) {
                    System.out.print("Type id of group to remove: ");
                    if (scanner.hasNextInt()) {
                        int idToDelete = scanner.nextInt();
                        scanner.nextLine();
                        Group groupToDelete = Group.loadById(conn, idToDelete);
                        groupToDelete.delete(conn);
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

