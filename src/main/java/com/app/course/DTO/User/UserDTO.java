package com.app.course.DTO.User;

import com.app.course.models.Course;
import com.app.course.models.Educator;
import com.app.course.models.RoleUser;
import com.app.course.models.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String avatar;
    private String role;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country; // đất nước
    private String zipCode; // mã bữu chính
    private String zipCodeCity;
}
