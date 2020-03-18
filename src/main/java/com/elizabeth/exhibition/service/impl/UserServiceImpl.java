package com.elizabeth.exhibition.service.impl;

import com.elizabeth.exhibition.dao.UserDao;
import com.elizabeth.exhibition.domain.User;
import com.elizabeth.exhibition.entity.UserEntity;
import com.elizabeth.exhibition.service.UserService;
import com.elizabeth.exhibition.service.mapper.Mapper;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final Mapper<UserEntity, User> userMapper;

    public UserServiceImpl(UserDao userDao, Mapper<UserEntity, User> userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public void register(User user) {
        userDao.save(userMapper.mapDomainToEntity(user));
    }

    @Override
    public boolean login(String email, String password) {
        Optional<User> temp = userDao.findByEmail(email).map(userMapper::mapEntityToDomain);
        return temp.map(user -> user.getPassword().equals(password)).orElse(false);
    }

    @Override
    public Optional<User> isUserExist(String email) {
        Optional<UserEntity> temp = userDao.findByEmail(email);
        return temp.map(userMapper::mapEntityToDomain);
    }
}
