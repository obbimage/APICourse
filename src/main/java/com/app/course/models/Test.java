package com.app.course.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Test {

    /*PRIMARY KEY */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /* SET FOREIGN KEY */

    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;
    @JsonIgnore
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private Set<Question> questions;
    /*  SET COLUM  */
    private boolean complete;

    public Test(){}

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

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
