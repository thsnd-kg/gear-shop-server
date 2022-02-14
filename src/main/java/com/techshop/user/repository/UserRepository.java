package com.techshop.user.repository;

import com.techshop.user.dto.UserDto;
import com.techshop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u") // JPQL
    List<UserDto> findAllUserDto();

    int countByUsername(String username);

    int countByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.groups WHERE u.username = ?1")
    Optional<User> findByUsernameWithGroups(String username);

}