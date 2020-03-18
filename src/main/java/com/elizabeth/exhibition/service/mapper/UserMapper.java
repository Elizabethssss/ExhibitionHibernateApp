package com.elizabeth.exhibition.service.mapper;


import com.elizabeth.exhibition.domain.User;
import com.elizabeth.exhibition.entity.UserEntity;
import com.elizabeth.exhibition.service.PasswordEncryptor;

import java.util.Optional;

public class UserMapper implements Mapper<UserEntity, User> {
    private final PasswordEncryptor passwordEncryptor;

    public UserMapper(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public User mapEntityToDomain(UserEntity entity) {
        return User.builder()
                .withId(entity.getId())
                .withUsername(entity.getUsername())
                .withEmail(entity.getEmail())
                .withPassword(entity.getPassword())
                .build();
    }

    @Override
    public UserEntity mapDomainToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(Optional.ofNullable(user.getId()).orElse(0L));
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncryptor.encrypt(user.getPassword()));
        return userEntity;
    }
}
