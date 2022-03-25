package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connect = Util.getConnection();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = connect.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(65), " +
                    "lastName VARCHAR(100), " +
                    "age TINYINT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connect.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prStatement = connect.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            prStatement.setString(1, name);
            prStatement.setString(2, lastName);
            prStatement.setByte(3, age);
            prStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement prStatement = connect.prepareStatement("DELETE FROM users WHERE id = ?")) {
            prStatement.setLong(1, id);
            prStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = connect.createStatement().executeQuery("SELECT id, name, lastname, age FROM users")) {
            while (resultSet.next()) {
                User person = new User();
                person.setId(resultSet.getLong("id"));
                person.setName(resultSet.getString("name"));
                person.setLastName(resultSet.getString("lastname"));
                person.setAge(resultSet.getByte("age"));
                users.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connect.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
