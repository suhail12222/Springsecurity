package com.practices.demo.service;

import com.practices.demo.dto.LoginDto;
import com.practices.demo.dto.LoginResponseDto;
import com.practices.demo.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserService userService;
    private  final AuthenticationManager authenticationManager;
    public LoginResponseDto login(LoginDto loginDto){
        Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        UserEntity user=(UserEntity)authenticate.getPrincipal();
        String acessToken=jwtService.generateSecritKey(user);
        String refreshToken= jwtService.getRefreshToken(user);
        return new LoginResponseDto(user.getId(), acessToken,refreshToken);
    }

    public LoginResponseDto refrehToken(String refreshToken) {
        Long userId=jwtService.getValidate(refreshToken);
        UserEntity user=userService.getUserByID(userId);
        String acessToken=jwtService.generateSecritKey(user);
        return new LoginResponseDto(user.getId(),acessToken,refreshToken);
    }
}
