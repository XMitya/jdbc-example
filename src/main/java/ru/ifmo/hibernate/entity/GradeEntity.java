package ru.ifmo.hibernate.entity;

import javax.persistence.*;

@Table(name = "grade")
@Entity
public class GradeEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "grade_id_gen", sequenceName = "grade_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "grade_id_gen")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @Column(name = "grade", columnDefinition = "SMALLINT")
    private Short grade;

    public GradeEntity() {
    }

    public GradeEntity(Integer id, CourseEntity course, TeacherEntity teacher, StudentEntity student, Short grade) {
        this.id = id;
        this.course = course;
        this.teacher = teacher;
        this.student = student;
        this.grade = grade;
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

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public Short getGrade() {
        return grade;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "GradeEntity{" +
                "id=" + id +
                ", course=" + course +
                ", teacher=" + teacher +
                ", student=" + student +
                ", grade=" + grade +
                '}';
    }
}
