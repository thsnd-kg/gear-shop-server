package com.techshop.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techshop.common.entity.BaseEntity;
import com.techshop.role.entity.Group;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"groups"})
@EqualsAndHashCode(exclude = {"groups"}, callSuper = false)
@Entity
@Table(name = "techshop_user")
public class User extends BaseEntity {
    @NotNull
    @Column(unique = true)
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "users")
    private Set<Group> groups = new HashSet<>();
}
