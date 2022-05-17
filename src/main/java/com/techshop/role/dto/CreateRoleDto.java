package com.techshop.role.dto;

import com.techshop.role.validation.annotation.UniqueRoleName;
import com.techshop.role.validation.annotation.ValidDescription;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class CreateRoleDto {
	private String name;
	private String description;
}
