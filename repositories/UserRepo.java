package com.practices.demo.repositories;

import com.practices.demo.entities.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
  Optional<UserEntity> findByEmail(String email);
}
