package pl.coderslab.app;

import pl.coderslab.Models.DbManager;
import pl.coderslab.Models.Exercise;
import pl.coderslab.Models.Group;
import java.sql.Connection;
import java.sql.SQLException;

public class testExercise {
    public static void main(String[] args) {

        DbManager db = DbManager.getInstance();
            Connection conn = db.getConnection();
            Exercise[] exercises = Exercise.loadAll(conn);
            for (Exercise e : exercises) {
                System.out.println(e);
            }
            Exercise ex= Exercise.loadById(conn,4);
            ex.setDescription("ooooopis");
            ex.saveToDB(conn);
            ex.delete(conn);


    }
}
