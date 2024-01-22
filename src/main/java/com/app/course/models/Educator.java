package com.app.course.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="educator")
public class Educator implements Serializable {
    @Id
    private long id; // using the same primary key as User
    private String biography;
    private String description;
    @JsonIgnore
    @OneToOne
    @MapsId // gia trị được sao chép từ user
    private User user;

    public Educator() {
    }

    public Educator(long id, String biography, String description, User user) {
        this.id = id;
        this.biography = biography;
        this.description = description;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
