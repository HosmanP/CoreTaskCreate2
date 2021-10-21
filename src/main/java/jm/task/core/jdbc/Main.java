package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Иван", "Иванов", (byte) 12);
        user.saveUser("Петр", "Щеглов", (byte) 44);
        user.saveUser("Лея", "Петровна", (byte) 23);
        user.saveUser("Остап", "Валиев", (byte) 12);
        users = user.getAllUsers();
        System.out.println(users);
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
