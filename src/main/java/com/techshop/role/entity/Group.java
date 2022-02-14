package com.techshop.role.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techshop.common.entity.BaseEntity;
import com.techshop.user.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"roles", "users"})
@EqualsAndHashCode(exclude = {"roles", "users"}, callSuper = false)
@Entity
@Table(name = "techshop_group")
public class Group extends BaseEntity {
	@NotNull
	@Size(min = 3, max = 50, message = "{group.name.size}")
	private String name;
	
	private String description;
	
	@Builder.Default
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "techshop_group_role",
	joinColumns = @JoinColumn(name = "group_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@Builder.Default
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "techshop_group_user",
	joinColumns = @JoinColumn(name = "group_id"),
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users = new HashSet<>();
	
	// helpers
	public void addRole(Role role) {
		roles.add(role);
		role.getGroups().add(this);
	}
	
	public Group addUser(User user) {
		users.add(user);
		user.getGroups().add(this);
		return this;
	}
}
