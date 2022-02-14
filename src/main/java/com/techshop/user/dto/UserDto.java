package com.techshop.user.dto;

import com.techshop.role.entity.Group;

import java.util.Set;

public interface UserDto {
    public String getUsername();

    public String getEmail();

    public Set<Group> getGroups();
}
