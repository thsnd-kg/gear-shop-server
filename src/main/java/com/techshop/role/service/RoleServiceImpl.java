package com.techshop.role.service;
import com.techshop.role.dto.CreateRoleDto;
import com.techshop.role.dto.RoleDto;
import com.techshop.role.dto.UpdateRoleDto;
import com.techshop.role.entity.Role;
import com.techshop.role.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

// concrete
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	private RoleRepository repository;

	
	@Override
	public List<RoleDto> findAll() {
		return repository.findAllDto();
	}

	@Override
	public Role addNewRole(CreateRoleDto dto) {
		Role newRole = new Role();
		
		newRole.setName(dto.getName().toUpperCase());
		newRole.setDescription(dto.getDescription());
		
		return repository.save(newRole);
	}

	@Override
	public boolean isTakenName(String roleName) {
		return repository.countByName(roleName.toUpperCase()) >= 1;
	}

	@Override
	public boolean isExistedId(Long roleId) {
		return repository.existsById(roleId);
	}

	@Override
	public Role update(UpdateRoleDto dto, Long id) {
		Role role = repository.getById(id);
		
		role.setName(dto.getName().toUpperCase()); // add uppercase
		role.setDescription(dto.getDescription());
		
		return repository.save(role);
	}

	@Override
	public void deleteById(Long roleId) {
		repository.deleteById(roleId);;
	}

}
