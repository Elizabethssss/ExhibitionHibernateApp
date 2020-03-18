package com.elizabeth.exhibition.dao;

import com.elizabeth.exhibition.entity.UserEntity;

import java.util.Optional;

public interface UserDao extends GenericDao<UserEntity> {
    Optional<UserEntity> findByEmail(String email);
}
