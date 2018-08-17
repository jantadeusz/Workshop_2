package pl.coderslab.Models;

import sun.util.calendar.LocalGregorianCalendar;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Solution {

    private int id;
    private Timestamp created;
    private Timestamp updated;
    private String description;
    private int exerciseId;
    private int userId;
    private int gradeVal;
    private String gradeComment;


    public Solution() {
    }

    public void setGradeVal(int gradeVal) {
        this.gradeVal = gradeVal;
    }

    public void setGradeComment(String gradeComment) {
        this.gradeComment = gradeComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated() {
        Timestamp now = new Timestamp(new java.util.Date().getTime());
        this.created = now;
    }

    public void setCreated(Timestamp timestamp) {
        this.created = timestamp;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated() {
        Timestamp now = new Timestamp(new java.util.Date().getTime());
        this.updated = now;
    }

    public void setUpdated(Timestamp timestamp) {
        this.updated = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static String getCreateTabSolution() {
        return createTabSolution;
    }

    public static void setCreateTabSolution(String createTabSolution) {
        Solution.createTabSolution = createTabSolution;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                ", exerciseId=" + exerciseId +
                ", userId=" + userId +
                ", gradeVal=" + gradeVal +
                ", gradeComment=" + gradeComment +
                '}';
    }

    //============================= database methods ============================================

    static public Solution[] loadAll(Connection conn) {
        List<Solution> solutions = new ArrayList<>();
        String sql = "SELECT * FROM solution;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Solution solution = getSolutionFromResultSet(rs);
                solutions.add(solution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Solution[] result = new Solution[solutions.size()];
        result = solutions.toArray(result);
        return result;
    }

    static public Solution loadById(Connection conn, int id) {
        String sql = "SELECT * FROM solution Where id=?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Solution solution = getSolutionFromResultSet(rs);
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Connection conn) {
        try {
            if (this.id != 0) {
                String sql = "DELETE FROM solution WHERE id=?;";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, this.id);
                ps.executeUpdate();
                this.id = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveToDB(Connection conn) {
        try {
            if (this.id == 0) {
                this.setCreated();
                this.setUpdated();
                String sql = "INSERT INTO solution(created, updated, description, exercise_id, user_id) VALUES (?,?,?,?,?);";
                String generatedColumns[] = {"id"};
                PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
                ps.setTimestamp(1, this.created);
                ps.setTimestamp(2, this.updated);
                ps.setString(3, this.description);
                ps.setInt(4, this.exerciseId);
                ps.setInt(5, this.userId);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                    this.id = rs.getInt(1);
            } else {
                this.setUpdated();
                String sql = "UPDATE solution SET updated = ?, description = ? WHERE id = ?;";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setTimestamp(1, this.updated);
                ps.setString(2, this.description);
                ps.setInt(3, this.id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveGradeToDB(Connection conn) {
        try {
            String sql = "UPDATE solution SET grade_val = ?, grade_comment = ? WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, this.gradeVal);
            ps.setString(2, this.gradeComment);
            ps.setInt(3, this.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public Solution[] loadAllByUserId(Connection conn, int id) {
        List<Solution> solutions = new ArrayList<>();
        String sql = "SELECT * FROM solution where user_id=?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Solution solution = getSolutionFromResultSet(rs);
                solutions.add(solution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Solution[] result = new Solution[solutions.size()];
        result = solutions.toArray(result);
        return result;
    }

    static public Solution[] loadAllByExerciseId(Connection conn, int id) {
        List<Solution> solutions = new ArrayList<>();
        String sql = "SELECT * FROM solution WHERE exercise_id = ? ORDER BY updated desc;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Solution solution = getSolutionFromResultSet(rs);
                solutions.add(solution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Solution[] result = new Solution[solutions.size()];
        result = solutions.toArray(result);
        return result;
    }

    static public Solution[] loadSolvedWithoutGrade(Connection conn) {
        List<Solution> solutions = new ArrayList<>();
        String sql = "SELECT * FROM solution where description is not null and grade_val is null;;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Solution solution = getSolutionFromResultSet(rs);
                solutions.add(solution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Solution[] result = new Solution[solutions.size()];
        result = solutions.toArray(result);
        return result;
    }

    private static Solution getSolutionFromResultSet(ResultSet rs) {
        Solution solution = new Solution();
        try {
            solution.setId(rs.getInt("id"));
            solution.setCreated(rs.getTimestamp("created"));
            solution.setUpdated(rs.getTimestamp("updated"));
            solution.setDescription(rs.getString("description"));
            solution.setExerciseId(rs.getInt("exercise_id"));
            solution.setUserId(rs.getInt("user_id"));
            solution.setGradeVal(rs.getInt("grade_val"));
            solution.setGradeComment(rs.getString("grade_comment"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return solution;
    }

    public static String createTabSolution = "CREATE TABLE `solution` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `created` datetime DEFAULT NULL,\n" +
            "  `updated` datetime DEFAULT NULL,\n" +
            "  `description` text COLLATE utf8_polish_ci,\n" +
            "  `exercise_id` int(11) DEFAULT NULL,\n" +
            "  `user_id` bigint(20) DEFAULT NULL,\n" +
            "  'grade_val' INT DEFAULT NULL,\n" +
            "  'grade_comment' MEDIUMTEXT DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  KEY `fk_solution_1_idx` (`user_id`),\n" +
            "  KEY `fk_solution_2_idx` (`exercise_id`),\n" +
            "  CONSTRAINT `fk_solution_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `fk_solution_2` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;";
}
