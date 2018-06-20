package ru.ifmo.addressbook.hibernate;

import ru.ifmo.addressbook.Address;
import ru.ifmo.addressbook.Contact;
import ru.ifmo.addressbook.Phone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();

        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        Contact c = new Contact(null, "Petr");
        em.persist(c);

        System.out.println("Contact ID: " + c.getId());

        c.getAddresses().add(new Address(null, "some addr", c));
        c.getPhones().add(new Phone(null, "+71234567890", c));

        em.getTransaction().commit();

        CyclicBarrier barrier = new CyclicBarrier(2);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> concurrentUpdate(barrier)).start();
        }

//        JPAUtil.shutdown();
    }

    private static void concurrentUpdate(CyclicBarrier barrier) {
        EntityManagerFactory f = JPAUtil.getEntityManagerFactory();
        EntityManager em = f.createEntityManager();

        em.getTransaction().begin();

        Contact found = em.find(Contact.class, 1, LockModeType.OPTIMISTIC);

        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        found.setName("Goga " + ThreadLocalRandom.current().nextInt());

        em.persist(found);

        em.getTransaction().commit();

        em.getTransaction().begin();
        found.setName("Vasya");
        em.getTransaction().commit();
    }
}
