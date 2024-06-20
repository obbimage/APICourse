package com.app.course.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id tu tang
    private long id;
    @OneToOne(mappedBy = "user",cascade =  CascadeType.ALL) // cascadeType.All khi xoa use thi ben kia cung xoa theo
    @PrimaryKeyJoinColumn
    private Educator educator;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Student student;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Course> courses;
    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "list_role_user",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_user_id")}
    )
    private Set<RoleUser> roleUsers = new HashSet<>();
    private double wallet = 0;
    @Column(nullable = false)
    private boolean enabled = false;
    private String firstName;
    private String lastName;
    private String avatar;
    @Column(nullable = false)
    private String role;
    @Column(unique = true)
    private String email;
    private String phone;
    private String address;

    private String city;
    private String country; // đất nước
    private String zipCode; // mã bữu chính

    private String zipCodeCity;

    public  void createEducator(){
        Educator educator1  = new Educator(this);
        setEducator(educator1);
    }
    public void createUser(){
        Student student1 = new Student(this);
        setStudent(student1);
    }
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public Educator getEducator() {
//        return educator;
//    }
//
//    public void setEducator(Educator educator) {
//        this.educator = educator;
//        this.educator.setUser(this);
//    }
//
//    public Set<RoleUser> getRoleUsers() {
//        return roleUsers;
//    }
//
//    public void setRoleUsers(Set<RoleUser> roleUsers) {
//        this.roleUsers = roleUsers;
//    }
//
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//        this.student.setUser(this);
//    }
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public Set<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(Set<Course> courses) {
//        this.courses = courses;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getZipCode() {
//        return zipCode;
//    }
//
//    public void setZipCode(String zipCode) {
//        this.zipCode = zipCode;
//    }
//
//    public String getZipCodeCity() {
//        return zipCodeCity;
//    }
//
//    public void setZipCodeCity(String zipCodeCity) {
//        this.zipCodeCity = zipCodeCity;
//    }

}
