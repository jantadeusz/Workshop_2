package pl.coderslab.app;

import pl.coderslab.Models.DbManager;
import pl.coderslab.Models.User;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.util.Scanner;

public class controllerStudent {

    public static void main(String[] args) {
        Connection conn = DbManager.getInstance().getConnection();

        System.out.println("Welcome in Student panel ");
        Scanner scan = new Scanner(System.in);
        System.out.print("Type your userName: ");
        String login = scan.nextLine();
        User[] users = User.loadAllUsers(conn);
        int id = User.getIdFromUserName(users, login);
        System.out.println("Id is: " + id);

    }
}
