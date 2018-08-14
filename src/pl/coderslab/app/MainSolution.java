package pl.coderslab.app;

import pl.coderslab.Models.DbManager;
import pl.coderslab.Models.Solution;
import pl.coderslab.Models.User;

import java.sql.Connection;
import java.sql.SQLException;

public class MainSolution {

    public static void main(String[] args) {

        DbManager db = DbManager.getInstance();


        try {
            Connection conn = db.getConnection();
//==================== create new solution ================================
//            Solution solution = new Solution();
//            solution.setDescription("description of solution X");
//            solution.setExerciseId(3);
//            solution.setUserId(1);
//============================== update existing solution =================
//            Solution solution = Solution.loadById(conn, 6);
//            solution.setDescription("after timestamp");
//            solution.saveToDB(conn);

            System.out.println("===== load all solutions of user with spec id=========");
            Solution[] solutions = Solution.loadAllByUserId(conn, 1);
            for (Solution s : solutions) {
                System.out.println(s);
            }

            System.out.println("===== load all solutions of exercise with spec id=========");
            Solution[] solutions1 = Solution.loadAllByExerciseId(conn, 3);
            for (Solution s : solutions1) {
                System.out.println(s);
            }
            System.out.println("===== load all users from spec group =========");
            User[] users = User.loadAllByGroupId(conn, 3);
            for (User u : users) {
                System.out.println(u);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

