package ru.ifmo;

/**
 * Created by xmitya on 18.01.17.
 */
public class Course {
    private int id;
    private String title;
    private int duration;
    private double price;

    public Course() {
    }

    public Course(int id, String title, int duration, double price) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
