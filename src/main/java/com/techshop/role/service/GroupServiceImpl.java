package com.techshop.role.service;

import com.techshop.role.dto.AddRoleDto;
import com.techshop.role.dto.AddUserDto;
import com.techshop.role.dto.CreateGroupDto;
import com.techshop.role.dto.GroupDto;
import com.techshop.role.entity.Group;
import com.techshop.role.entity.Role;
import com.techshop.role.repository.GroupRepository;
import com.techshop.role.repository.RoleRepository;
import com.techshop.user.entity.User;
import com.techshop.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {
	private final GroupRepository repository;
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	
	@Override
	public boolean isTakenName(String groupName) {
		return repository.countByName(groupName) >= 1;
	}

	@Override
	public List<GroupDto> findAll() {
		return repository.findAllDto();
	}

	@Override
	public Group add(CreateGroupDto dto) {
		Group group = new Group();
		group.setName(dto.getName());
		group.setDescription(dto.getDescription());
		
		return repository.save(group);
	}

	@Override
	public boolean isExisted(Long groupId) {
		return repository.existsById(groupId);
	}

	@Override
	public Group addRole(AddRoleDto dto) {
		Group group = repository.getById(dto.getGroupId());
		Role role = roleRepository.getById(dto.getRoleId());
		group.addRole(role);
		return repository.save(group);
	}

	@Override
	@Transactional
	public Group addUser(AddUserDto dto) {
		Group group = repository.getById(dto.getGroupId());
		User user = userRepository.getById(dto.getUserId());
		
		return group.addUser(user);
	}

}
