package com.techshop.role.dto;


import com.techshop.role.validation.annotation.ValidNewRoleName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ValidNewRoleName
public class UpdateRoleDto {
	@NotNull
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String oldName;
	
	private String description;
}
