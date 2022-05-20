package com.techshop.user.controller;

import com.techshop.common.ResponseHandler;
import com.techshop.user.dto.ChangePasswordDto;
import com.techshop.user.dto.CreateUserDto;
import com.techshop.user.dto.UpdateUserDto;
import com.techshop.user.entity.User;
import com.techshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService userService){
        this.service = userService;
    }


    @GetMapping(path = "/users")
    public Object getUsers(){
        try{
            return ResponseHandler.getResponse(service.getUsers(), HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/customers")
    public Object getCustomers(){
        try{
            return ResponseHandler.getResponse(service.getCustomers(), HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/users")
    public Object createUser(@Valid @RequestBody CreateUserDto dto, BindingResult errors) {
        try{
            if(errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            User addedUser = service.createUser(dto);

            return ResponseHandler.getResponse(addedUser, HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/users")
    public Object updateUser(@Valid @RequestBody UpdateUserDto dto, BindingResult errors) {
        try{
            if(errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            User updatedUser = service.updateUser(dto);

            return ResponseHandler.getResponse(updatedUser, HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(path= "/users/{username}")
    public Object deleteUser(@PathVariable("username") String username) {
        try{
            service.deleteUserByUsername(username);

            return ResponseHandler.getResponse( HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /*Website*/
    @GetMapping(path = "/profile/me")
    public Object getProfile(){
        try{
            return ResponseHandler.getResponse(service.getProfile(), HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/profile/me")
    public Object updateProfile(@RequestBody UpdateUserDto dto, BindingResult errors) {
        try{
            if(errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            dto.setRoleId(null);
            User updatedUser = service.updateUser(dto);

            return ResponseHandler.getResponse(updatedUser, HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/profile/me/change-password")
    public Object changePassword(@RequestBody ChangePasswordDto dto, BindingResult errors) {
        try{
            if(errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

             service.changePassword(dto);

            return ResponseHandler.getResponse(HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
