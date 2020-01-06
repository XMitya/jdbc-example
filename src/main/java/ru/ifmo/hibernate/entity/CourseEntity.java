package ru.ifmo.hibernate.entity;

import javax.persistence.*;

@Table(name = "course")
@Entity
public class CourseEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "sourse_id_gen", sequenceName = "course_id_seq")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "duration", columnDefinition = "SMALLINT")
    private Short duration;

    @Column(name = "price", columnDefinition = "NUMERIC")
    private Double price;

    public CourseEntity() {
    }

    public CourseEntity(Integer id, String title, Short duration, Double price) {
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

    public Short getDuration() {
        return duration;
    }

    public void setDuration(Short duration) {
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
