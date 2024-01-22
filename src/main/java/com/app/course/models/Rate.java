package com.app.course.models;

import com.app.course.models.keys.StudentCourseKey;
import jakarta.persistence.*;

@Entity
@Table(name = "rate")
public class Rate {
    @EmbeddedId
    StudentCourseKey id;
    @ManyToOne
    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @MapsId("student_id)")
    @JoinColumn(name = "student_id")
    private Student student;

    /*  SET COLUM  */
    private int rate;

    public StudentCourseKey getId() {
        return id;
    }

    public void setId(StudentCourseKey id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String comment;
}
