package com.app.course.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name ="subRole" )
public class SubRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "subRole")
    private Set<Course> courses;

    public SubRole(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
