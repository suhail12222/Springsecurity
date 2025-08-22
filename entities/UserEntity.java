package com.practices.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.modelmapper.internal.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Getter
@Setter

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
