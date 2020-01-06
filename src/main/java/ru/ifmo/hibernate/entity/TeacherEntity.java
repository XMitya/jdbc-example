package ru.ifmo.hibernate.entity;

import javax.persistence.*;

@Table(name = "teacher")
@Entity
public class TeacherEntity {
    @Id
    @SequenceGenerator(name = "teacher_id_gen", sequenceName = "teacher_id_seq")
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Column(name = "name")
    private String name;

    @Column(name = "salary", columnDefinition = "NUMERIC")
    private double salary;

    public TeacherEntity() {
    }

    public TeacherEntity(Integer id, CourseEntity course, String name, double salary) {
        this.id = id;
        this.course = course;
        this.name = name;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "id=" + id +
                ", course=" + course +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
