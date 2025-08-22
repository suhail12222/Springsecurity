package com.practices.demo.service;

import com.practices.demo.dto.LoginDto;
import com.practices.demo.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;
    public  String login(LoginDto loginDto){
        Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        UserEntity user=(UserEntity)authenticate.getPrincipal();
        String token=jwtService.generateSecretKey(user);
        return token;
    }
}
