package ru.ifmo.pattern.entity;

/**
 * Created by xmitya on 18.01.17.
 */
public class CourseEntity {
    private Integer id;
    private String title;
    private Integer duration;
    private Double price;

    public CourseEntity() {
    }

    public CourseEntity(Integer id, String title, Integer duration, Double price) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}
