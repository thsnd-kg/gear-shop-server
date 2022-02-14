package com.techshop.role.controller;

import com.techshop.common.ResponseHandler;
import com.techshop.role.dto.AddRoleDto;
import com.techshop.role.dto.AddUserDto;
import com.techshop.role.dto.CreateGroupDto;
import com.techshop.role.dto.GroupDto;
import com.techshop.role.entity.Group;
import com.techshop.role.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/group")
public class GroupController {
	private GroupService service;
	
	public GroupController(GroupService groupService) {
		service = groupService;
	}
	
	@GetMapping
	public Object findAllGroup() {
		List<GroupDto> groups = service.findAll();
		
		return ResponseHandler.getResponse(groups, HttpStatus.OK);
	}
	
	@PostMapping
	public Object createGroup(@Valid @RequestBody CreateGroupDto dto,
			BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Group newGroup = service.add(dto);
		return ResponseHandler.getResponse(newGroup, HttpStatus.OK);
	}
	
	@PutMapping
	public Object updateGroup() {
		return null;
	}
	
	@PostMapping("/add-role")
	public Object addRoleToGroup(@Valid @RequestBody AddRoleDto dto, BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Group updatedGroup = service.addRole(dto);
		
		return ResponseHandler.getResponse(updatedGroup, HttpStatus.OK);
	}
	
	@PostMapping("/remove-role")
	public Object removeRoleFromGroup() {
		return null;
	}
	
	@PostMapping("/add-user")
	public Object addUserToGroup(@Valid @RequestBody AddUserDto dto, BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Group updatedGroup = service.addUser(dto);
		
		return ResponseHandler.getResponse(updatedGroup, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public Object deleteGroup(@PathVariable("id") Long groupId) {
		return null;
	}
}
