package com.techshop.user.service;

import com.techshop.user.dto.BlockedUserDto;
import com.techshop.user.dto.ChangePasswordDto;
import com.techshop.user.dto.CreateUserDto;
import com.techshop.user.dto.UpdateUserDto;
import com.techshop.user.entity.User;

import java.util.List;

public interface UserService {


    boolean isTakenUsername(String username);

    boolean isTakenEmail(String email);

    User createUser(CreateUserDto dto);

    List<User> getUsers();

    List<User> getCustomers();

    void deleteUserByUsername(String username);

    User getUserByUsername(String username);

    User updateUser(UpdateUserDto dto);

    User getProfile();

    void changePassword(ChangePasswordDto dto);

    void changeStatus(BlockedUserDto dto);
}
