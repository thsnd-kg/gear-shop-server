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
	@NotBlank(message = "{role.name.not-blank}")
	@Size(min = 3, max = 50, message = "{role.name.size}")
	@UniqueRoleName(message = "{role.name.used}")
	private String name;
	
	@ValidDescription
	private String description;
}
