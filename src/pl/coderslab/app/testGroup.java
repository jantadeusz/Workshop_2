package pl.coderslab.app;

import pl.coderslab.Models.DbManager;
import pl.coderslab.Models.Group;

import java.sql.Connection;
import java.sql.SQLException;

public class testGroup {

    public static void main(String[] args) {

        DbManager db = DbManager.getInstance();
        Connection conn = db.getConnection();
        System.out.println("=========================all==============");
        Group[] groups = Group.loadAll(conn);
        for (Group g : groups) {
            System.out.println(g);
        }

        System.out.println("=========================spec id==============");
        Group g = Group.loadById(conn, 5);
        System.out.println(g);
//            g.delete(conn);
        g.setName("redd");
        g.saveToDB(conn);
        Group newGroup = new Group("pink");
        newGroup.saveToDB(conn);

    }


}
