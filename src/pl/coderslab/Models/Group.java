package pl.coderslab.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Group {

    private int id;
    private String name;

    public Group() {
    }

    public Group(String name) {
        this.id = id;
        this.name = name;
    }

    public static String getCreateTabGroup() {
        return createTabGroup;
    }

    public static void setCreateTabGroup(String createTabGroup) {
        Group.createTabGroup = createTabGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

//============================= database methods ============================================

    public static String createTabGroup = "CREATE TABLE `User_group` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `name` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;\n";

    static public Group[] loadAll(Connection conn){//} throws SQLException {
        List<Group> groups = new ArrayList<>();
        String sql = "SELECT * FROM User_group;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
                groups.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Group[] result = new Group[groups.size()];
        result = groups.toArray(result);
        return result;
    }

    static public Group loadById(Connection conn, int id){

        try {
            String sql = "SELECT * FROM User_group where id =?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Connection conn){
        try {
            if (this.id != 0) {
                String sql = "DELETE FROM User_group WHERE id=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, this.id);
                ps.executeUpdate();
                this.id = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveToDB(Connection conn){
        try {
            if (this.id == 0) {
                String sql = "INSERT INTO User_group(name) VALUES (?)";
                String generatedColumns[] = {"id"};
                PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
                ps.setString(1, this.name);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                    this.id = rs.getInt(1);
            } else {
                String sql = "UPDATE User_group SET name=? where id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, this.name);
                ps.setInt(2, this.id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
