package com.app.course.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {
    // -------set primer key------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // -----set fk------
    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Unit> unit;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;
    @OneToMany(mappedBy = "course")
    private List<LevelRequire> levelRequires;
    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Test> test;
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<Buy> buys;
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<Rate> rates;
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "subRole_id")
    private SubRole subRole;
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<StudentWillLearn> studentWillLearns;
    @JsonIgnore
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<WhoCourse> whoCourses;

    // -------set colum-------
    @Column(columnDefinition = "LONGTEXT")
    private String summary;  // Tóm tắt khóa học
    @Column(columnDefinition = "LONGTEXT")
    private String clipDemo;
    private String img;
    private String name;
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private long price;

    private String dateUpload;
    private String  dateUpdate;
    private boolean confirm;
    private boolean complete; // khóa học đã xong tạo hay chưa


}
