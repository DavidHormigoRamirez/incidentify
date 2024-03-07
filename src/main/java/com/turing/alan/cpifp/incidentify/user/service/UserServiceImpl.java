package com.turing.alan.cpifp.incidentify.user.service;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import com.turing.alan.cpifp.incidentify.user.domain.UserEntity;
import com.turing.alan.cpifp.incidentify.user.domain.UserRepository;
import com.turing.alan.cpifp.incidentify.user.exception.UserAlreadyExistsException;
import com.turing.alan.cpifp.incidentify.user.exception.UserDoesNotExistsException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repository,PasswordEncoder encoder) {
        this.userRepository = repository;
        this.encoder = encoder;
    }

    @Override
    public List<UserEntity> getAll() {
        return (List<UserEntity>) this.userRepository.findAll();
    }

    @Override
    public UserEntity create(UserEntity entity) {

        if (userRepository.existsByEmail(entity.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        String encodedPassword = this.encoder.encode(entity.getPassword());
        entity.setPassword(encodedPassword);

        return this.userRepository.save(entity);
    
    }

    @Override
    public UserEntity getOne(Long id) {
        return this.userRepository.findById(id).orElseThrow(
            ()->new UserDoesNotExistsException()
        );
    }

    @Override
    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            this.userRepository.deleteById(id);
        }
        else {
            throw new UserDoesNotExistsException();
        }
        
    }

    @Override
    public UserEntity update(UserEntity updatedEntity) {
        
        long id = updatedEntity.getId();

        UserEntity currentEntity = getOne(id);

        // Copiamos todas las propiedades, excepto el id y el email
        // que no son actualizables
        BeanUtils.copyProperties(updatedEntity, 
                                 currentEntity,
                                 "id","email");
        
        return this.userRepository.save(currentEntity);

    }

    @Override
    public UserEntity getByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(
            ()->new UserDoesNotExistsException()
        );
    }


    
}
