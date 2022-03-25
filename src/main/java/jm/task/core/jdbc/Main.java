package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Dmitrii", "Ivanov", (byte)25);
        userService.saveUser("Elena", "Sokolova", (byte)33);
        userService.saveUser("Marina", "Egorova", (byte)22);
        userService.saveUser("Rudik", "Sergeev", (byte)40);

        userService.removeUserById(3);
        userService.getAllUsers();
        userService.cleanUsersTable();


        userService.dropUsersTable();
    }
}
