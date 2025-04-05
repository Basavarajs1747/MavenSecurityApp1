package com.ums.controller;

import com.ums.payload.LoginDto;
import com.ums.payload.UserDto;
import com.ums.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")

//http://localhost:8080/api/v1/auth/addUser
public class AuthController {

    private  UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        UserDto dto = userService.addUser(userDto);
        return  new  ResponseEntity<>("user registered success", HttpStatus.CREATED);
    }
   @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
       String token = userService.verifyLogin(loginDto);
       System.out.println(token);
       if (token!=null) {
           return new ResponseEntity<>(token,HttpStatus.OK);
       }
       return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
       }

   }