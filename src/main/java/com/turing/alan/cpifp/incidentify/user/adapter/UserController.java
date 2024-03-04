package com.turing.alan.cpifp.incidentify.user.adapter;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turing.alan.cpifp.incidentify.user.domain.UserEntity;
import com.turing.alan.cpifp.incidentify.user.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping("/users")
    public Iterable<UserEntity> getAll() {
        return this.service.getAll();
    }

    @PostMapping("/users")
    public UserEntity create(@RequestBody final UserEntity entity) {
        return this.service.create(entity);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable(name = "id") final long id) {
        this.service.delete(id);
    }

    @GetMapping("/users/{id}")
    public UserEntity getOne(@PathVariable(name = "id") final Long id) {
        return this.service.getOne(id);
    }

    @PutMapping("/users/{id}")
    public UserEntity update(@PathVariable(name="id") long id, @RequestBody UserEntity entity) {
        return this.service.update(entity, id);
    }

    
}
