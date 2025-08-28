package com.practices.demo.service;

import com.practices.demo.dto.SignupDto;
import com.practices.demo.dto.UserDto;
import com.practices.demo.entities.UserEntity;
import com.practices.demo.exception.ResourceNotFoundException;
import com.practices.demo.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.OptionalInt;

@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//@Autowired
//    public UserService(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user with this email "+username+"is not present"));
    }
    public UserDto signUp(SignupDto signupDto) {
//        Optional<UserEntity> user=UserRepo.findByEmail(SignupDto.getEmail());
    Optional <UserEntity> user= userRepo.findByEmail(signupDto.getEmail());
  if (user.isPresent()){
      throw new BadCredentialsException("user with this email exist");
  }
  UserEntity toCreate= modelMapper.map(signupDto,UserEntity.class);
  toCreate.setPassword(passwordEncoder.encode(toCreate.getPassword()));
  UserEntity saved=userRepo.save(toCreate);
  return modelMapper.map(saved,UserDto.class);

}

    public UserEntity getUserByID(Long userId) {
        UserEntity user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("yate chai mummd ame id sth lad gude ishfaqs"));
        return user;
    }
}
