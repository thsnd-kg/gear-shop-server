package com.techshop.user.service;

import com.techshop.user.dto.CreateUserDto;
import com.techshop.user.dto.UserDto;
import com.techshop.user.entity.User;

import java.util.List;

public interface UserService {

    List<UserDto> findAllDto();

    boolean isTakenUsername(String username);

    boolean isTakenEmail(String email);

    User createUser(CreateUserDto dto);

}
