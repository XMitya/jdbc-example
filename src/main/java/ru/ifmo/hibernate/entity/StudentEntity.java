package ru.ifmo.hibernate.entity;

import javax.persistence.*;

@Table(name = "student")
@Entity
public class StudentEntity {
    @Id
    @SequenceGenerator(name = "student_id_gen", sequenceName = "student_id_seq")
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @Column(name = "name")
    private String name;

    public StudentEntity() {
    }

    public StudentEntity(Integer id, CourseEntity course, TeacherEntity teacher, String name) {
        this.id = id;
        this.course = course;
        this.teacher = teacher;
        this.name = name;
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

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", course=" + course +
                ", teacher=" + teacher +
                ", name='" + name + '\'' +
                '}';
    }
}
