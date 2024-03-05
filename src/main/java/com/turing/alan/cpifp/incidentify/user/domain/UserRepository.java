package com.turing.alan.cpifp.incidentify.user.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,Long> {
    
    public boolean existsByEmail(String email);
    public Optional<UserEntity> findByEmail(String email);
    
}
