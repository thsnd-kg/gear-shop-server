package com.techshop.security.controller;

import com.techshop.common.ResponseHandler;
import com.techshop.security.dto.LoginDto;
import com.techshop.security.jwt.JwtUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder encoder;

    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils) {
        authenticationManager = authManager;
        this.jwtUtils = jwtUtils;
    }


    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginDto dto, BindingResult errors) {
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Authentication auth = null;
        System.out.println(encoder.encode(dto.getPassword()));

        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtUtils.generateJwtToken(auth);
            // log history - AOP
            return ResponseHandler.getResponse(token, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("{} has been logged in with wrong password: {}" + dto.getUsername() + e.getMessage() );
        }

        return ResponseHandler.getResponse("Username or password is invalid.", HttpStatus.BAD_REQUEST);
    }
}
