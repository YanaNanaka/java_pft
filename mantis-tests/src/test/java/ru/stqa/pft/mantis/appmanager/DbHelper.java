package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;


public class DbHelper {
    private final SessionFactory sessionFactory;
    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Users users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Session sessionHelper = getSession();
        List<UserData> result = session.createQuery("from UserData").list();
        session.getTransaction().commit();
        session.close();
        return new Users(result);
    }

    public void deleteUser(int id) {
        UserData user = new UserData();
        user.setId(id);
        Session session = getSession();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }


    private Session getSession() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

}