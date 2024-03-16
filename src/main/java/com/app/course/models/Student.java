package com.app.course.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    /*  PRIMARY KEY  */
    @Id
    @JoinColumn(name = "account_id")
    private long id;
    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private Set<Buy> buys;
    @JsonIgnore
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private Set<Rate> rates;
    /*  SET COLUM  */
    private int level;
    private String experience;

    public Student() {
    }


    public Student(User user, Set<Buy> buys, int level, String experience) {
        this.user = user;
        this.buys = buys;
        this.level = level;
        this.experience = experience;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Buy> getBuys() {
        return buys;
    }

    public void setBuys(Set<Buy> buys) {
        this.buys = buys;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
