package ru.ifmo.hibernate;

import java.util.List;

public interface Dao<T> {
    void save(T t);
    T get(Integer id);
    List<T> getAll();
    void remove(Integer id);
}
