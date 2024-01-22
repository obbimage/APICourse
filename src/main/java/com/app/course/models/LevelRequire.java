package com.app.course.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "levelRequire")
public class LevelRequire {
    /*  PRIMARY KEY  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /*  SET COLUM  */
    @Column(unique = true)
    private String level;

    @JsonIgnore
    @OneToMany(mappedBy = "levelRequire")
    private Set<Course> courses;

    public LevelRequire(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
