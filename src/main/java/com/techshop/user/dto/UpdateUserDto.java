package com.techshop.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techshop.role.entity.Role;
import com.techshop.user.validation.annotation.UniqueEmail;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class UpdateUserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String address;
    private String imgUrl;
    private Long roleId;
    private String dateOfBirth;

    @Email
    @UniqueEmail
    private String email;
}
