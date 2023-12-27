package com.gb.lesson04.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Jpa {
    public static void main(String[] args) throws SQLException {
        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<Book> books = LongStream.rangeClosed(1, 10)
                    .mapToObj(it -> new Book(it))
                    .peek(it -> it.setName("Book #" + it.getId()))
                    .peek(it -> session.persist(it))
                    .collect(Collectors.toList());

            session.getTransaction().commit();
        }

        final Book loadedUser;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loadedUser = session.get(Book.class, 1);
            loadedUser.setName("Updated");
            session.getTransaction().commit();
        }


        try (Session session = sessionFactory.openSession()) {
            List<Book> books = session.createQuery("select id, name from books where id >= 1", Book.class)
                    .getResultList();

            System.out.println(books);
        }

        sessionFactory.close();
    }
}
