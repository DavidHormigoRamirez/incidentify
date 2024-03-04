package com.turing.alan.cpifp.incidentify.user.service;

import org.springframework.stereotype.Service;
import com.turing.alan.cpifp.incidentify.user.domain.UserEntity;
import com.turing.alan.cpifp.incidentify.user.domain.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository repository){
        this.userRepository = repository;
    }

    @Override
    public Iterable<UserEntity> getAll() {
        return this.userRepository.findAll();
        
    }

    @Override
    public UserEntity create(UserEntity entity) {
        return this.userRepository.save(entity);
    }

    @Override
    public UserEntity getOne(Long id) {
        return this.userRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserEntity update(UserEntity entity, Long id) {
        
        return this.userRepository.save(entity);

    }


    
}
