package com.app.course.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String avatar;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country; // đất nước
    private String zipCode; // mã bữu chính
    private String zipCodeCity;
}
