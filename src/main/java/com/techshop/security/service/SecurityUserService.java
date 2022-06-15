package com.techshop.security.service;

import com.techshop.security.dto.ForgotPasswordDto;
import com.techshop.user.entity.PasswordResetToken;
import com.techshop.user.entity.User;
import com.techshop.user.entity.VerificationToken;

public interface SecurityUserService {
//    String validatePasswordResetToken(String username, String token);
//
//    String getForgotPasswordToken(String email);
    VerificationToken createVerificationToken(User user);
    Boolean verifyMailToken(String VerificationToken);
    PasswordResetToken  createPasswordResetToken(String email);
    void verifyPasswordResetToken(ForgotPasswordDto dto);

}
