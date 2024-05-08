package com.app.course.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

// Khóa học dành cho ai
@Entity
@Table(name = "WhoCourse")
public class WhoCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;
    private String whoCourse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getWhoCourse() {
        return whoCourse;
    }

    public void setWhoCourse(String whoCourse) {
        this.whoCourse = whoCourse;
    }
}
