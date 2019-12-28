package ru.ifmo.pattern.entity;

public class GradeEntity {
    private Integer id;
    private Integer courseId;
    private Integer teacherId;
    private Integer studentId;
    private Integer grade;

    public GradeEntity() {
    }

    public GradeEntity(Integer id, Integer courseId, Integer teacherId, Integer studentId, Integer grade) {
        this.id = id;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.grade = grade;
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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "GradeEntity{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", teacherId=" + teacherId +
                ", studentId=" + studentId +
                ", grade=" + grade +
                '}';
    }
}
