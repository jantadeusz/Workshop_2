package pl.coderslab.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Exercise {


    private int id;
    private String title;
    private String description;

    public Exercise() {
    }

    public static String getCreateTabExercise() {
        return createTabExercise;
    }

    public static void setCreateTabExercise(String createTabExercise) {
        Exercise.createTabExercise = createTabExercise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    //============================= database methods ============================================

    public static String createTabExercise = "CREATE TABLE `exercise` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `title` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,\n" +
            "  `description` text COLLATE utf8_polish_ci,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;";

    static public Exercise[] loadAll(Connection conn){
        List<Exercise> exercises = new ArrayList<>();
        String sql = "SELECT * FROM exercise;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));
                exercises.add(exercise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Exercise[] result = new Exercise[exercises.size()];
        result = exercises.toArray(result);
        return result;
    }

    static public Exercise loadById(Connection conn, int id){

        String sql = "SELECT * FROM exercise where id =?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));
                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Connection conn){//} throws SQLException {
        try {
            if (this.id != 0) {
                String sql = "DELETE FROM exercise WHERE id=?;";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, this.id);
                ps.executeUpdate();
                this.id = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveToDB(Connection conn){//} throws SQLException {
        try {
            if (this.id == 0) {
                String sql = "INSERT INTO exercise(title, description) VALUES (?,?);";
                String generatedColumns[] = {"id"};
                PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
                ps.setString(1, this.title);
                ps.setString(2, this.description);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                    this.id = rs.getInt(1);
            } else {
                String sql = "UPDATE exercise SET title = ?, description = ? WHERE id = ?;";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, this.title);
                ps.setString(2, this.description);
                ps.setInt(3, this.id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
