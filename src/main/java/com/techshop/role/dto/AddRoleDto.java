package com.techshop.role.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AddRoleDto {
	@NotNull
	@Min(value = 1)
	private Long groupId;
	
	@NotNull
	@Min(value = 1)
	private Long roleId;
}
