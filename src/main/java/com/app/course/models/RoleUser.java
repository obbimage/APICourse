package com.app.course.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roleUser")
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "roleUser")
//    private Set<User> users = new HashSet<>();
}
