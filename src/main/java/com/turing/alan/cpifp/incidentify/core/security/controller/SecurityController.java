package com.turing.alan.cpifp.incidentify.core.security.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turing.alan.cpifp.incidentify.user.adapter.UserMapper;
import com.turing.alan.cpifp.incidentify.user.adapter.UserModifyDTO;
import com.turing.alan.cpifp.incidentify.user.adapter.UserReadDTO;
import com.turing.alan.cpifp.incidentify.user.domain.UserEntity;
import com.turing.alan.cpifp.incidentify.user.exception.UserAlreadyExistsException;
import com.turing.alan.cpifp.incidentify.user.service.UserService;

import jakarta.validation.Valid;

@RestController
public class SecurityController {
    
    private final UserService service;
    private final UserMapper mapper;

    public SecurityController(UserService service,UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public UserReadDTO login(Principal principal){
        String email = principal.getName();
        return mapper.mapToReadDTO(service.getByEmail(email));
        
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(CsrfToken token) {
        return token;
    }

    @PostMapping("/register")
    public ResponseEntity<UserReadDTO> create(@Valid @RequestBody final UserModifyDTO modifyUser, Errors errors) {
        
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserEntity entity = mapper.mapToEntity(modifyUser);
        try {
            return new ResponseEntity<UserReadDTO>( mapper.mapToReadDTO(this.service.create(entity)), HttpStatus.CREATED);

        }
        catch (UserAlreadyExistsException exception) {
            return null;
        }
        
    }


}
