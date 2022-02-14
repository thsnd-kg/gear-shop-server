package com.techshop.role.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techshop.common.entity.BaseEntity;
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
@ToString(exclude = {"groups", "programs"})
@EqualsAndHashCode(exclude = {"groups", "programs"}, callSuper = false)
@Entity
@Table(name = "techshop_role")
public class Role extends BaseEntity {
	@NotNull
	@Size(min = 3, max = 50)
	@Column(unique = true)
	private String name;
	
	private String description;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "roles")
	@Builder.Default
	private Set<Group> groups = new HashSet<>();
	

}
