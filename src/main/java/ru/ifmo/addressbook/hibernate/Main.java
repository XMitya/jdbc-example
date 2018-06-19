package ru.ifmo.addressbook.hibernate;

import ru.ifmo.addressbook.Address;
import ru.ifmo.addressbook.Contact;
import ru.ifmo.addressbook.Phone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
    }
}
