package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Иван", "Иванов", (byte) 12);
        userServiceImpl.saveUser("Петр", "Щеглов", (byte) 44);
        userServiceImpl.saveUser("Лея", "Петровна", (byte) 23);
        userServiceImpl.saveUser("Остап", "Валиев", (byte) 12);
        users = userServiceImpl.getAllUsers();
        System.out.println(users);
        userServiceImpl.cleanUsersTable();
        userServiceImpl.getAllUsers();
    }
}
