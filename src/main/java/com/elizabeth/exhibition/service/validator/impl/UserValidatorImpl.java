package com.elizabeth.exhibition.service.validator.impl;

import com.elizabeth.exhibition.domain.User;
import com.elizabeth.exhibition.exception.ErrorTypes;
import com.elizabeth.exhibition.exception.FailException;
import com.elizabeth.exhibition.service.validator.UserValidator;

import java.util.Optional;

public class UserValidatorImpl implements UserValidator {
    private static final String EMAIL_PATTERN = "(\\w+\\.?)+@(\\w+\\.?)+";

    @Override
    public void validate(User user) {
        final Optional<User> optionalUser = Optional.ofNullable(user);
        optionalUser.map(User::getEmail).filter(x -> x.matches(EMAIL_PATTERN)).orElseThrow(() ->
                new FailException(ErrorTypes.EMAIL_INPUT_ERROR));
    }
}
