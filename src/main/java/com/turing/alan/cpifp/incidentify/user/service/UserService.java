package com.turing.alan.cpifp.incidentify.user.service;

import com.turing.alan.cpifp.incidentify.core.CustomRestService;
import com.turing.alan.cpifp.incidentify.user.domain.UserEntity;

public interface UserService extends CustomRestService<UserEntity,Long> {
    
    public UserEntity getByEmail(String email);

}
