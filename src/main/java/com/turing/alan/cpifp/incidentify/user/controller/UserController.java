package com.turing.alan.cpifp.incidentify.user.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turing.alan.cpifp.incidentify.user.adapter.UserMapper;
import com.turing.alan.cpifp.incidentify.user.adapter.UserModifyDTO;
import com.turing.alan.cpifp.incidentify.user.adapter.UserReadDTO;
import com.turing.alan.cpifp.incidentify.user.domain.UserEntity;
import com.turing.alan.cpifp.incidentify.user.exception.UserAlreadyExistsException;
import com.turing.alan.cpifp.incidentify.user.service.UserService;

import jakarta.validation.Valid;

import java.util.stream.Collectors;
import java.util.List;

@RestController
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service,UserMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/users")
    public List<UserReadDTO> getAll() {
        return this.service.getAll()
        .stream()
        .map(mapper::mapToReadDTO)
        .collect(Collectors.toList());

    }

    @GetMapping("/users/{id}")
    public UserReadDTO getOne(@PathVariable(name = "id") final Long id) {
        return  mapper.mapToReadDTO(this.service.getOne(id));
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable(name = "id") final long id) {
        this.service.delete(id);
    }
 
    @PostMapping("/users")
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


    @PutMapping("/users/{id}")
    public UserReadDTO update(@PathVariable(name="id") long id, @RequestBody UserModifyDTO entity) {
        
        UserEntity updateEntity = mapper.mapToEntity(entity);  
        updateEntity.setId(id);
        return mapper.mapToReadDTO(service.update(updateEntity));
    }

    
}
