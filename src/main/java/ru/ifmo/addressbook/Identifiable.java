package ru.ifmo.addressbook;

public abstract class Identifiable {
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
