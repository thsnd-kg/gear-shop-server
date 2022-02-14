package com.techshop.role.service;


import com.techshop.role.dto.AddRoleDto;
import com.techshop.role.dto.AddUserDto;
import com.techshop.role.dto.CreateGroupDto;
import com.techshop.role.dto.GroupDto;
import com.techshop.role.entity.Group;

import java.util.List;

public interface GroupService {

	boolean isTakenName(String groupName);

	List<GroupDto> findAll();

	Group add(CreateGroupDto dto);

	boolean isExisted(Long groupId);

	Group addRole(AddRoleDto dto);

	Group addUser(AddUserDto dto);

}
