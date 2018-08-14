package pl.coderslab.app;

import pl.coderslab.Models.DbManager;
import pl.coderslab.Models.User;

import java.sql.Connection;
import java.sql.SQLException;

public class MainUser {

    public static void main(String[] args) {

        User userAdams = new User("Adams", "adam@wp.pl", "1234as",3);
        User userMonic = new User("Monic", "monika@wp.pl", "1234er",4);
        User userTeddy = new User("Teddy", "teddysf@wp.pl", "1234uy",3);
        User userBundy = new User("Bundy", "albundy@wp.pl", "1234hy",4);


        DbManager db = DbManager.getInstance();

        try {
            Connection conn = db.getConnection();


//            User userNull = new User();
//            userNull.saveToDB(conn); // wywala blad username cannot be null
//            userAdams.saveToDB(conn);
//            userMonic.saveToDB(conn);
//            userTeddy.saveToDB(conn);


            User userFromDb1 = User.loadUserById(conn, 1);
            User userFromDb2 = User.loadUserById(conn, 2);
            User userFromDb3 = User.loadUserById(conn, 3);
            User userFromDb4 = User.loadUserById(conn, 4);
            User userFromDb5 = User.loadUserById(conn, 5);

            System.out.println(userFromDb1);
            System.out.println(userFromDb2);
            System.out.println(userFromDb3);
            System.out.println(userFromDb4);
            System.out.println(userFromDb5);

            System.out.println("------------------------------ load all users and print them in console -------------");
            User[] tableOfUsers = User.loadAllUsers(conn);
            for (User e : tableOfUsers) {
                System.out.println(e);
            }

            System.out.println("------------------------------ load specific user and print in console --------------");
            System.out.println(tableOfUsers[1]);
            User userToModify = User.loadUserById(conn, 3);
            System.out.println(userToModify);

            System.out.println("----------- modification of user and print him in console after modification --------");
            try {
                userToModify.setPassword("7777abc");
                userToModify.setEmail("monikakalata@gmail.com");
                userToModify.saveToDB(conn);
                System.out.println(User.loadUserById(conn, 3));
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            System.out.println("------------------------ delete user and print all in console -----------------------");
//            User userToDelete = User.loadUserById(conn, 8);
//            try {
//                userToDelete.delete(conn);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            User[] tableOfUsers1 = User.loadAllUsers(conn);
//            for (User e : tableOfUsers1) {
//                System.out.println(e);
//            }
//            System.out.println("--------------- check id of deleted user --------------------------------------------");
//            System.out.println(userToDelete);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
