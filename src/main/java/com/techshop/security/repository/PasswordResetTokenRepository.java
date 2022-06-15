package com.techshop.security.repository;

import com.techshop.user.entity.PasswordResetToken;
import com.techshop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
        Optional<PasswordResetToken> findByToken(String token);
        PasswordResetToken findByUser(User user);
}
