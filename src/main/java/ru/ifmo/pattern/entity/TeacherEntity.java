package ru.ifmo.pattern.entity;

public class TeacherEntity {
    private Integer id;
    private Integer courseId;
    private String name;
    private double salary;

    public TeacherEntity() {
    }

    public TeacherEntity(Integer id, Integer courseId, String name, Double salary) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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
                ", courseId=" + courseId +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
