package com.practices.demo.controllers;

import com.practices.demo.dto.LoginDto;
import com.practices.demo.dto.PostDto;
import com.practices.demo.dto.SignupDto;
import com.practices.demo.dto.UserDto;
import com.practices.demo.service.AuthService;
import com.practices.demo.service.PostService;
import com.practices.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
@PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
    UserDto userDto= userService.signUp(signupDto);
    return ResponseEntity.ok(userDto);
}
@PostMapping("/signin")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
    String token= authService.login(loginDto);
    return ResponseEntity.ok(token);

    }
}
