package com.elizabeth.exhibition.service;

import com.elizabeth.exhibition.domain.User;

import java.util.Optional;

public interface UserService {
    void register(User user);
    boolean login(String username, String password);
    Optional<User> isUserExist(String email);
}
