package com.turing.alan.cpifp.incidentify.core.security.configuration;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.turing.alan.cpifp.incidentify.user.domain.UserEntity;
import com.turing.alan.cpifp.incidentify.user.domain.UserRepository;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
       
    private final UserRepository repository;
    public DatabaseUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserEntity userEntity = this.repository.findByEmail(username).orElseThrow(
            () -> new UsernameNotFoundException(username)
        );

        UserDetails userDetails = User.builder()
                                      .username(userEntity.getEmail())
                                      .password(userEntity.getPassword())
                                      //.roles("ADMIN")
                                      .roles(userEntity.getRole())
                                     .build();
        return userDetails;


    }
}
