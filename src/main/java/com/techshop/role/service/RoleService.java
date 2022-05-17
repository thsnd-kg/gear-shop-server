package com.techshop.role.service;

import com.techshop.role.dto.CreateRoleDto;
import com.techshop.role.dto.RoleDto;
import com.techshop.role.dto.UpdateRoleDto;
import com.techshop.role.entity.Role;

import javax.validation.Valid;
import java.util.List;

// abstraction
public interface RoleService {
	// contract
	List<RoleDto> findAll();

	Role addNewRole(CreateRoleDto dto);

	boolean isTakenName(String roleName);

	Role getRoleById(Long roleId);

	Role update(UpdateRoleDto dto, Long id);

	void deleteById(Long roleId);

	boolean isExistedId(Long roleId);
}
