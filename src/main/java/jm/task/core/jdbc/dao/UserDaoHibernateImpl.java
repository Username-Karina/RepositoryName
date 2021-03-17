package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction tr = null;
        String createTable = "create table if not exists user ("
                + "id bigint not null auto_increment primary key , "
                + "name varchar(20) not null,"
                + "lastname varchar(50) not null ,"
                + "age tinyint not null)";
        try{
            session = Util.getSessionFactory().openSession();
            tr = session.beginTransaction();
            NativeQuery query = session.createSQLQuery(createTable).addEntity(User.class);
            query.executeUpdate();
            tr.commit();
            System.out.println("Table exist!");
        }catch(Exception e){
            if(tr !=null){
                tr.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                session.close();
            }catch (HibernateException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction tr = null;
        String drop = "drop table if exists user";

        System.out.println("Table not exist!");
        try{
            session = Util.getSessionFactory().openSession();
            tr = session.beginTransaction();
            NativeQuery query = session.createSQLQuery(drop).addEntity(User.class);
            query.executeUpdate();
            tr.commit();
        }catch(Exception e){
            if(tr !=null){
                tr.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                session.close();
            }catch (HibernateException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction tr = null;

        try{
            session = Util.getSessionFactory().openSession();
            tr = session.beginTransaction();
            User user = new User(name,lastName,age);
            session.save(user);
            session.flush();

            tr.commit();
            System.out.println("User saved");
        }catch(Exception e){
            if(tr !=null){
                tr.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                session.close();
            }catch (HibernateException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction tr = null;

        try{
            session = Util.getSessionFactory().openSession();
            tr = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);

            tr.commit();
            System.out.println("User delete");
        }catch(Exception e){
            if(tr !=null){
                tr.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                session.close();
            }catch (HibernateException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        Transaction tr = null;
        List<User> users = null;
        try {
            session = Util.getSessionFactory().openSession();
            tr = session.beginTransaction();
            users = session.createNativeQuery("select * from user")
                    .addEntity(User.class)
                    .list();
            tr.commit();
        }catch(Exception e){
            if(tr !=null){
                tr.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                session.close();
            }catch (HibernateException e){
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction tr = null;
        String clean = "TRUNCATE TABLE USER";


        try{
            session = Util.getSessionFactory().openSession();
            tr = session.beginTransaction();
            NativeQuery query = session.createSQLQuery(clean);
            query.executeUpdate();
            tr.commit();
            System.out.println("Table is empty");

        }catch(Exception e){
            if(tr !=null){
                tr.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                session.close();
            }catch (HibernateException e){
                e.printStackTrace();
            }
        }
    }
}
