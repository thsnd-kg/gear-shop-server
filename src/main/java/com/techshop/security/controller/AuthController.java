package com.techshop.security.controller;

import com.techshop.common.ResponseHandler;
import com.techshop.mail.service.MailService;
import com.techshop.security.dto.ConfirmMailDto;
import com.techshop.security.dto.LoginDto;
import com.techshop.security.jwt.JwtUtils;
import com.techshop.security.service.SecurityUserService;
import com.techshop.user.dto.CreateUserDto;
import com.techshop.user.entity.User;
import com.techshop.user.entity.VerificationToken;
import com.techshop.user.repository.UserRepository;
import com.techshop.user.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final MailService mailService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SecurityUserService securityUserService;

    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils, UserService userService,  MailService mailService) {
        authenticationManager = authManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.mailService= mailService;
    }


    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginDto dto, BindingResult errors) {
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Authentication auth = null;

        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );

            User user = userService.getUserByUsername(dto.getUsername());
            if(user.getActiveFlag().equals("B"))
                return ResponseHandler.getResponse("Your account has been blocked", HttpStatus.OK);

            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtUtils.generateJwtToken(auth);
            // log history - AOP
            return ResponseHandler.getResponse(token, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("{} has been logged in with wrong password: {}" + dto.getUsername() + e.getMessage() );
        }

        return ResponseHandler.getResponse("Username or password is invalid.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/register")
    public Object register(@Valid @RequestBody CreateUserDto dto, BindingResult errors) {
        try{
            if(errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            dto.setRoleId(null);
            dto.setFlag("N");
            User addedUser = userService.createUser(dto);
            VerificationToken token =securityUserService.createVerificationToken(addedUser);
            mailService.sendVerifyMail(token.getUser().getEmail(), token.getToken());

            return ResponseHandler.getResponse(HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/confirm-email")
    public Object confirmEmail(@Param("token") String token) {
        try{
            securityUserService.verifyMailToken(token);
            return ResponseHandler.getResponse(HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping(path= "forgot-password/{email}")
    public Object forgotPassword(@PathVariable("email") String email) {
        try{
//
//            String token = securityUserService.getForgotPasswordToken(email);


            return ResponseHandler.getResponse(HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
