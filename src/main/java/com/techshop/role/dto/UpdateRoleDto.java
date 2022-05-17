package com.techshop.role.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UpdateRoleDto {
	@NotNull
	private Long id;
	
	@NotBlank
	private String name;
	
	private String description;
}
