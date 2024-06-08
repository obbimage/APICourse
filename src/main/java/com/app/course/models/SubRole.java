package com.app.course.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private boolean isAllow;

}
