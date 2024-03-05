package com.turing.alan.cpifp.incidentify.user.adapter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.turing.alan.cpifp.incidentify.user.domain.UserEntity;

@Component
public class UserMapper {

    public UserReadDTO mapToReadDTO(UserEntity user) {
        UserReadDTO userRead = new UserReadDTO();
        BeanUtils.copyProperties(user,userRead);
        return userRead;
    }

    public UserEntity mapToEntity(UserModifyDTO userModify){

        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(userModify, entity);
        return entity;
    }
    
}
