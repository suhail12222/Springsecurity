package com.practices.demo.controllers;

import com.practices.demo.dto.*;
import com.practices.demo.entities.UserEntity;
import com.practices.demo.service.AuthService;
import com.practices.demo.service.JwtService;
import com.practices.demo.service.PostService;
import com.practices.demo.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
    private final JwtService jwtService;
@PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
    UserDto userDto= userService.signUp(signupDto);
    return ResponseEntity.ok(userDto);
} //   when user sigin we provide jwt token from here to maintain  for one min only next part will be to give refresh token after this token expiration.\\
@PostMapping("/signin")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest req, HttpServletResponse res){
    LoginResponseDto loginResponseDto=authService.login(loginDto);
    Cookie cookie=new Cookie("refreshToken", loginResponseDto.getRefreshToken());
    cookie.setHttpOnly(true);
    res.addCookie(cookie);
//    String token= authService.login(loginDto);
    return ResponseEntity.ok(loginResponseDto);

    }
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> getRefresh(HttpServletRequest req,HttpServletResponse res){
      String refreshToken=  Arrays.stream(req.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()->new AuthenticationServiceException("refreshToken not found inside the cookies"));

   LoginResponseDto loginResponseDto=authService.refrehToken(refreshToken);



   return  ResponseEntity.ok(loginResponseDto);
    }
}
