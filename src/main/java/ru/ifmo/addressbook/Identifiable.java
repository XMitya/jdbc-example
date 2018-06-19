package ru.ifmo.addressbook;

import javax.persistence.*;

@MappedSuperclass
public abstract class Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    protected Integer id;

    public Identifiable() {
    }

    public Identifiable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
