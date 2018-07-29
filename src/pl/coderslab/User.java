package pl.coderslab;
// na repo Adama szukaj szablonu dbManager do projektu v2

//tu masz przoda: :)
//https://github.com/awlodarczyk/Warsztaty_2

import java.sql.PreparedStatement;

public class User {

    private int id;
    private String name;
    private String userName;
    private String password;
    private String email;

    public User(String name, String userName, String password, String email) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;

    }

    public User() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Active record
    public void saveToDB() {
        //insert update
        if (this.id == 0) {
            //insert try catch
            String sql = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
            String generatedColumns[] = {"ID"};
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);

            } else {
                //update try catch
                String sql = "UPDATE users SET username=?, email=?, password=? where id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, this.username);
                preparedStatement.setString(2, this.email);
                preparedStatement.setString(3, this.password);
                preparedStatement.setInt(4, this.id);
                preparedStatement.executeUpdate();
            }

        }
    }

    public void delete() {

        String sql = "DELETE FROM users WHERE id=?";
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, this.id);
        preparedStatement.executeUpdate();
        this.id = 0;
    }

}

