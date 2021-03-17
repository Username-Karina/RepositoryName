package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;


public class Main {

    public static void main(String[] args) {

        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();

        userDao.saveUser("Meg","Ryan",(byte) 50);
        userDao.saveUser("Mike","Fox",(byte) 69);
        userDao.saveUser("AJ","Cook",(byte) 30);

        userDao.getAllUsers();

        userDao.removeUserById(1);

        userDao.getAllUsers();

        userDao.cleanUsersTable();

        userDao.dropUsersTable();







    }
}
