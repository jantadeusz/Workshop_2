package pl.coderslab.Models;

import org.mindrot.BCrypt;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String salt;
    private int personGroupId;

    public User() {
    }

    public User(String username, String email, String password, int personGroupId) {
        this.username = username;
        this.email = email;
        this.setPassword(password);
        this.personGroupId = personGroupId;
    }

    public int getPersonGroupId() {
        return personGroupId;
    }

    public void setPersonGroupId(int personGroupId) {
        this.personGroupId = personGroupId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, this.salt);
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void saveToDB(Connection conn) {

        try {
            if (this.id == 0) {
                String sql = "INSERT INTO Users(username, email, password, user_group_id, salt) VALUES (?,?,?,?,?)";
                String generatedColumns[] = {"ID"};
                PreparedStatement ps;
                ps = conn.prepareStatement(sql, generatedColumns);
                ps.setString(1, this.username);
                ps.setString(2, this.email);
                ps.setString(3, this.password);
                ps.setInt(4, this.personGroupId);
                ps.setString(5, this.salt);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                    this.id = rs.getInt(1);
            } else {
                String sql = "UPDATE Users SET username=?, email=?, password=?, user_group_id=?, salt =? where id = ?";
                PreparedStatement ps;
                ps = conn.prepareStatement(sql);
                ps.setString(1, this.username);
                ps.setString(2, this.email);
                ps.setString(3, this.password);
                ps.setInt(4, this.personGroupId);
                ps.setString(5, this.salt);
                ps.setInt(6, this.id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public User loadUserById(Connection conn, int id) {

        try {
            String sql = "SELECT * FROM Users where id=?";
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User loadedUser = getUserFromResultSet(resultSet);
                return loadedUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public User[] loadAllUsers(Connection conn) {
        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM Users";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User loadedUser = getUserFromResultSet(resultSet);
                users.add(loadedUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User[] uArray = new User[users.size()];
        uArray = users.toArray(uArray);
        return uArray;
    }

    private static User getUserFromResultSet(ResultSet resultSet) {
        User loadedUser = new User();
        try {
            loadedUser.id = resultSet.getInt("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            loadedUser.personGroupId = resultSet.getInt("user_group_id");
            loadedUser.salt = resultSet.getString("salt");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loadedUser;
    }

    public void delete(Connection conn) {
        try {
            if (this.id != 0) {
                String sql = "DELETE FROM Users WHERE id=?";
                PreparedStatement preparedStatement;
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, this.id);
                preparedStatement.executeUpdate();
                this.id = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public User[] loadAllByGroupId(Connection conn, int id) {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE user_group_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User loadedUser = getUserFromResultSet(rs);
                users.add(loadedUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User[] uArray = new User[users.size()];
        uArray = users.toArray(uArray);
        return uArray;
    }

    public static String createTabUsers = "CREATE TABLE `Workshop_2`.`Users` (\n" +
            "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `email` VARCHAR(255) NOT NULL,\n" +
            "  `username` VARCHAR(255) NOT NULL,\n" +
            "  `password` VARCHAR(60) NOT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE INDEX `email_UNIQUE` (`email` ASC));\n";

    public static int getIdFromUserName(User[] users, String input) {
        int resultId = -1;
        for (User u : users) {
            if (input.equals(u.getUsername())) {
                resultId = u.getId();
            }
        }
        return resultId;
    }
}

