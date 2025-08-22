package com.practices.demo.service;

import com.practices.demo.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {
    //getting secret key from here
//   @Value("${jwt.secretkey}")
//    private String jwtSecretKey;
//    private String token;
//    public SecretKey getSecretKey(){
//        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
//    }
//    public String generateToken(UserEntity user){
//        return
//                Jwts.builder()
//                        .subject(user.getId().toString())
//                        .claim("email",user.getEmail())
//                        .claim("role",Set.of("Admin","User","Dev"))
//                        .issuedAt(new Date())
//                        .expiration(new Date(System.currentTimeMillis()+1000*60))
//                        .signWith(getSecretKey()
//                        ).compact();
//    }
//public Long getIdFromToken(String token){
//        Claims claims=Jwts.parser()
//                .verifyWith(getSecretKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//        return Long.valueOf(claims.getSubject());
//    }
//    @Value("${jwt.secretkey}")
    @Value("${jwt.secretkey}")
    private String jwtSecretKey;
    private String token;
    public SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }
    public String generateSecretKey(UserEntity user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("role",Set.of("ADMIN","USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60))
                .signWith(getSecretKey())
                .compact();


    }
    //validate
    public Long getValidate(String token){
       Claims claims=Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
               .parseSignedClaims(token)
               .getPayload();
          return Long.valueOf(claims.getSubject());
    }
//    public Long GetId(String token){
//        JwtService j=new JwtService();
//        return Long.valueOf(j.getValidate(token));
//
//    }

}












/*
@Value("${jwt.secretkey}")
private String jwtSecretKey;


public SecretKey getSecretKey(){
    return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
}
public String generateToken(UserEntity user){
    return Jwts.builder()
            .subject(user.getId().toString())
            .claim("emal",user.getEmail())
            .claim("role", Set.of("Admin","user"))
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis()+100*60))
            .signWith(getSecretKey())
            .compact();
}
public Long getIdFromToken(String token){
    Claims claims=Jwts.parser()
            .verifyWith(getSecretKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    return Long.valueOf(claims.getSubject());
}
*/
//================================================================================


//    @Value("${jwt.secretkey}")
//    private String jwtSecurityKey;
//    private String token;
//
//    //getting decurity key of the previous key
//    private SecretKey getSecretKey(){
//        return Keys.hmacShaKeyFor(jwtSecurityKey.getBytes(StandardCharsets.UTF_8));
//    }
//    // generate token
//    public String generateToken(UserEntity userEntity){
//      return Jwts.builder()
//                .subject(userEntity.getId().toString())
//                .claim("email",userEntity.getEmail())
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis()+1000*60))
//                .signWith(getSecretKey())
//
//                .compact();
//
//
//    }
//    //breakdown token so that get id from that
//    public Long getUserIdFromToken(String token){
//        this.token = token;
//        Claims claims=Jwts.parser()
//                .verifyWith(getSecretKey())
//                .build()
//                .parseSignedClaims(token).getPayload();
//
//        return Long.valueOf(claims.getSubject());
