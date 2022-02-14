package com.techshop.role.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AddUserDto {
	@NotNull
	@Min(value = 1)
	private Long userId;
	
	@NotNull
	@Min(value = 1)
	private Long groupId;
}
