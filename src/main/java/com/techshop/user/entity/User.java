package com.techshop.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techshop.common.entity.BaseEntity;
import com.techshop.role.entity.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "techshop_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long userId;

    @NotNull
    @Column(unique = true)
    @Size(min = 3, max = 50)
    private String username;

    @JsonIgnore
    @NotNull
    private String password;

    private String firstName;
    private String lastName;
    private String phoneNo;
    private String address;
    private String imgUrl;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "varchar(1) default 'Y'")
    private String activeFlag = "Y";

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;



}
