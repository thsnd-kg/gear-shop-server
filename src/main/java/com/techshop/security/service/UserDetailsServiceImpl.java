package com.techshop.security.service;

import com.techshop.role.entity.Role;
import com.techshop.security.dto.UserDetailsDto;
import com.techshop.user.entity.User;
import com.techshop.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository repository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);

        if(!user.isPresent())
            throw new UsernameNotFoundException("Username is not existed.");

        Set<GrantedAuthority> authorities = new HashSet<>();
        if(user.get().getRole() == null)
            authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
        else
          authorities.add(new SimpleGrantedAuthority(user.get().getRole().getName()));

        return new UserDetailsDto(username, user.get().getPassword(), authorities);

    }

    private Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

}