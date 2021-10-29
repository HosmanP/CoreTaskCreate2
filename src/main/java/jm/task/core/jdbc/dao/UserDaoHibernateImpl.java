package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Transaction transaction = null;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS `mydb`.`user` (\n" +
                "  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                "  `name` TEXT NULL,\n" +
                "  `lastName` TEXT NULL,\n" +
                "  `age` INT NULL,\n" +
                "  PRIMARY KEY (`id`));\n";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Table created");
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS mydb.user;";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Table dropped");
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("User added");
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("DELETE FROM mydb.user WHERE id= :param1")
                .setParameter("param1", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("User deleted");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        userList = (ArrayList) session.createQuery("From " + User.class.getSimpleName()).list();
        session.getTransaction().commit();
        session.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        String sql = "TRUNCATE TABLE mydb.user;";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Table cleaned");
    }
}