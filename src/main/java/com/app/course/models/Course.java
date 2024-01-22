package com.app.course.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="course")
public class Course {
    // -------set primer key------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // -----set fk------
    @JsonIgnore
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private Set<Unit> unit;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;
    @ManyToOne
    @JoinColumn(name = "levelRequire_id")
    private LevelRequire levelRequire;
    @JsonIgnore
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private Set<Test> test;
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<Buy> buys;
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<Rate> rates;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "subRole_id")
    private SubRole subRole;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


    // -------set colum-------
    private String clipDemo;
    private String name;
    private String title;
    private String description;
    private String price;
    @Temporal(TemporalType.DATE)
    private Date dateUpload;
    private Date dateUpdate;
    private boolean confirm;

    public Course() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Unit> getUnit() {
        return unit;
    }

    public void setUnit(Set<Unit> unit) {
        this.unit = unit;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LevelRequire getLevelRequire() {
        return levelRequire;
    }

    public void setLevelRequire(LevelRequire levelRequire) {
        this.levelRequire = levelRequire;
    }

    public Set<Test> getTest() {
        return test;
    }

    public void setTest(Set<Test> test) {
        this.test = test;
    }

    public Set<Buy> getBuys() {
        return buys;
    }

    public void setBuys(Set<Buy> buys) {
        this.buys = buys;
    }

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public SubRole getSubRole() {
        return subRole;
    }

    public void setSubRole(SubRole subRole) {
        this.subRole = subRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getClipDemo() {
        return clipDemo;
    }

    public void setClipDemo(String clipDemo) {
        this.clipDemo = clipDemo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(Date dateUpload) {
        this.dateUpload = dateUpload;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
}
