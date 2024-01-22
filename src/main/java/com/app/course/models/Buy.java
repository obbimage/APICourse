package com.app.course.models;

import com.app.course.models.keys.StudentCourseKey;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "buy")
public class Buy implements Serializable {
    @EmbeddedId
    StudentCourseKey id;

    @ManyToOne
    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    private Student student;
    @Temporal(TemporalType.DATE)
    private Date dateBuy;

    private Boolean studentComplete;
    public Buy(){
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

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public Boolean getStudentComplete() {
        return studentComplete;
    }

    public void setStudentComplete(Boolean studentComplete) {
        this.studentComplete = studentComplete;
    }

    public StudentCourseKey getId() {
        return id;
    }

    public void setId(StudentCourseKey id) {
        this.id = id;
    }
}
