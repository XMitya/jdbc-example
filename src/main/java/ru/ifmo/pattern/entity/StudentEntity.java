package ru.ifmo.pattern.entity;

public class StudentEntity {
    private Integer id;
    private Integer courseId;
    private Integer teacherId;
    private String name;

    public StudentEntity() {
    }

    public StudentEntity(Integer id, Integer courseId, Integer teacherId, String name) {
        this.id = id;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.name = name;
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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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
                ", courseId=" + courseId +
                ", teacherId=" + teacherId +
                ", name='" + name + '\'' +
                '}';
    }
}
