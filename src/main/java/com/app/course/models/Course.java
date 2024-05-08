package com.app.course.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

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
    @JsonIgnore
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
    private String clipDemo;
    private String img;
    private String name;
    private String title;
    private String description;
    private String price;

    private String dateUpload;
    private String  dateUpdate;
    private boolean confirm;
    private boolean complete; // khóa học đã xong tạo hay chưa

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


    public List<LevelRequire> getLevelRequires() {
        return levelRequires;
    }

    public void setLevelRequires(List<LevelRequire> levelRequires) {
        this.levelRequires = levelRequires;
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

    public String getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(String dateUpload) {
        this.dateUpload = dateUpload;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public List<StudentWillLearn> getStudentWillLearns() {
        return studentWillLearns;
    }

    public void setStudentWillLearns(List<StudentWillLearn> studentWillLearns) {
        this.studentWillLearns = studentWillLearns;
    }

    public List<WhoCourse> getWhoCourses() {
        return whoCourses;
    }

    public void setWhoCourses(List<WhoCourse> whoCourses) {
        this.whoCourses = whoCourses;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean getComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
