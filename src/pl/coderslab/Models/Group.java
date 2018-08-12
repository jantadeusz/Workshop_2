package pl.coderslab.Models;

import java.sql.Connection;
import java.sql.SQLException;

public class Group {

    public static String createTabGroup = "CREATE TABLE `User_group` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `name` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;\n";

    public int id;
    public String name;


    public Group(int id, String name) {
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


//    static public Group loadGroupById(Connection conn) throws SQLException {
//
//        String sql = ""
//    }


    //    static public User loadUserById(Connection conn, int id) throws SQLException {
//
//        String sql = "SELECT * FROM Users where id=?";
//        PreparedStatement preparedStatement;
//        preparedStatement = conn.prepareStatement(sql);
//        preparedStatement.setInt(1, id);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        if (resultSet.next()) {
//            User loadedUser = new User();
//            loadedUser.id = resultSet.getInt("id");
//            loadedUser.username = resultSet.getString("username");
//            loadedUser.password = resultSet.getString("password");
//            loadedUser.email = resultSet.getString("email");
//            return loadedUser;
//        }
//        return null;
//    }
//    static public Group[] loadAllGroups(Connection conn) throws SQLException {
//        ArrayList<User> users = new ArrayList<User>();
//        String sql = "SELECT * FROM Users";
//        PreparedStatement preparedStatement;
//        preparedStatement = conn.prepareStatement(sql);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()) {
//            User loadedUser = new User();
//            loadedUser.id = resultSet.getInt("id");
//            loadedUser.username = resultSet.getString("username");
//            loadedUser.password = resultSet.getString("password");
//            loadedUser.email = resultSet.getString("email");
//            users.add(loadedUser);
//        }
//        User[] uArray = new User[users.size()];
//        uArray = users.toArray(uArray);
//        return uArray;
//    }
//
//    public void saveToDB(Connection conn) throws SQLException {
//
//        if (this.id == 0) {
//            String sql = "INSERT INTO Users(username, email, password) VALUES (?, ?, ?)";
//            String generatedColumns[] = {"id"};
//            PreparedStatement preparedStatement;
//            preparedStatement = conn.prepareStatement(sql, generatedColumns);
//            preparedStatement.setString(1, this.username);
//            preparedStatement.setString(2, this.email);
//            preparedStatement.setString(3, this.password);
//            preparedStatement.executeUpdate();
//            ResultSet rs = preparedStatement.getGeneratedKeys();
//            if (rs.next()) {
//                this.id = rs.getInt(1);
//            }
//        } else {
//            String sql = "UPDATE Users SET username=?, email=?, password=? where id = ?";
//            PreparedStatement preparedStatement;
//            preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setString(1, this.username);
//            preparedStatement.setString(2, this.email);
//            preparedStatement.setString(3, this.password);
//            preparedStatement.setInt(4, this.id);
//            preparedStatement.executeUpdate();
//        }
//
//    }
//

//
//
//    public void delete(Connection conn) throws SQLException {
//        if (this.id != 0) {
//            String sql = "DELETE FROM Users WHERE id=?";
//            PreparedStatement preparedStatement;
//            preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setInt(1, this.id);
//            preparedStatement.executeUpdate();
//            this.id = 0;
//        }
//    }


}
