package com.practices.demo.controllers;

import com.practices.demo.dto.LoginDto;
import com.practices.demo.dto.SignupDto;
import com.practices.demo.dto.UserDto;
import com.practices.demo.service.AuthService;
import com.practices.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
} //   when user sigin we provide jwt token from here to maintain  for one min only next part will be to give refresh token after this token expiration.\\
@PostMapping("/signin")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
    String token= authService.login(loginDto);
    return ResponseEntity.ok(token);

    }

//}
}
