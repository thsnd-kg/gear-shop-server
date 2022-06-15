package com.techshop.security.service;


//import com.techshop.security.repository.PasswordResetTokenRepository;
import com.techshop.security.repository.VerificationTokenRepository;
import com.techshop.user.entity.PasswordResetToken;
import com.techshop.user.entity.User;
import com.techshop.user.entity.VerificationToken;
import com.techshop.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class SecurityUserServiceImpl implements SecurityUserService{
    private VerificationTokenRepository verificationTokenRepository;
    private UserRepository userRepository;

    public SecurityUserServiceImpl( VerificationTokenRepository verificationTokenRepository, UserRepository userRepository){
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public VerificationToken createVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken myToken = new VerificationToken();
        myToken.setToken(token);
        myToken.setUser(user);
     ;
        return  verificationTokenRepository.save(myToken);
    }

    @Override
    public Boolean verifyMailToken(String VerificationToken) {
        Optional<VerificationToken> verification = verificationTokenRepository.findByToken(VerificationToken);
        if(!verification.isPresent())
            throw new IllegalStateException("Token xác thực không đúng");

        User user = verification.get().getUser();
        user.setActiveFlag("Y");
        userRepository.save(user);

        return true;
    }


//    private final PasswordResetTokenRepository passwordTokenRepository;

//    @Autowired
//    public SecurityUserServiceImpl(PasswordResetTokenRepository repository) {
//        this.passwordTokenRepository = repository;
//    }

//    @Override
//    public String validatePasswordResetToken(String username, String token) {
//        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
//        if ((passToken == null) || (passToken.getUser().getUsername()!= username)) {
//            return "invalid token";
//        }
//
//        final Calendar cal = Calendar.getInstance();
//        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//            return "expired";
//        }
//
//        return "valid token";
//
//    }
//
//    @Override
//    public String getForgotPasswordToken(String email) {
//        return null;
//    }

//    @Override
//    public String getForgotPasswordToken(String email) {
//        String token = UUID.randomUUID().toString();
//        userService.createPasswordResetTokenForUser(user, token);
//    }
}
