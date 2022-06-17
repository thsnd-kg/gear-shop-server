package com.techshop.user.service;

import com.techshop.role.entity.Role;
import com.techshop.role.service.RoleService;
import com.techshop.user.dto.BlockedUserDto;
import com.techshop.user.dto.ChangePasswordDto;
import com.techshop.user.dto.CreateUserDto;
import com.techshop.user.dto.UpdateUserDto;
import com.techshop.user.entity.User;
import com.techshop.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private PasswordEncoder encoder;
    private RoleService roleService;
    private  AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService, AuthenticationManager authenticationManager) {
        this.repository = userRepository;
        this.encoder = passwordEncoder;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public boolean isTakenUsername(String username) {
        return repository.countByUsername(username.toLowerCase()) >= 1;
    }

    @Override
    public boolean isTakenEmail(String email) {
        return repository.countByEmail(email) >= 1;
    }

    @Override
    public User createUser(CreateUserDto dto) {
        User newUser = new User();

        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(encoder.encode(dto.getPassword()));

        if(dto.getRoleId() != null){
            Role role = roleService.getRoleById(dto.getRoleId());
            newUser.setRole(role);
        }

        if(dto.getFlag() != null)
            newUser.setActiveFlag("N");

        return repository.save(newUser);
    }

    @Override
    public List<User> getUsers() {
        return repository.findUsers();
    }

    @Override
    public List<User> getCustomers() {
        return repository.findCustomers();
    }

    @Override
    public void deleteUserByUsername(String username) {
        User userDeleted = getUserByUsername(username);
        userDeleted.setActiveFlag("D");

        repository.save(userDeleted);
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = repository.findByUsername(username);
        if(!user.isPresent())
            throw new IllegalArgumentException("Username does not exist");

        return user.get();
    }

    @Override
    public User updateUser(UpdateUserDto dto) {
        User user = getUserByUsername(dto.getUsername());

        if(dto.getFirstName() != null)
            user.setFirstName(dto.getFirstName());

        if(dto.getLastName() != null)
            user.setLastName(dto.getLastName());

        if(dto.getPhoneNo() != null)
            user.setPhoneNo(dto.getPhoneNo());

        if(dto.getAddress() != null)
            user.setAddress(dto.getAddress() );

        if(dto.getImgUrl() != null)
            user.setImgUrl(dto.getImgUrl());

        if(dto.getEmail() != null)
            user.setEmail(dto.getEmail());

        if(dto.getDateOfBirth() != null)
            user.setDateOfBirth(dto.getDateOfBirth());

        if(dto.getRoleId() != null){
            Role newRole = roleService.getRoleById(dto.getRoleId());
            user.setRole(newRole);
        }

        return repository.save(user);
    }

    @Override
    public User getProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }
        User user = getUserByUsername(username);
        return user;
    }

    @Override
    public void changePassword(ChangePasswordDto dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, dto.getOldPassword())
            );

            User user = getUserByUsername(username);
            user.setPassword(encoder.encode((dto.getNewPassword())));
            repository.save(user);
        } catch(Exception e){
            throw new IllegalArgumentException("Old password does not match");
        }


    }

    @Override
    public void changeStatus(BlockedUserDto dto) {
        User user = getUserByUsername(dto.getUsername());
        user.setActiveFlag(dto.getFlag());
        repository.save(user);
    }

}
