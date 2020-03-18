package com.elizabeth.exhibition.dao.impl;


import com.elizabeth.exhibition.dao.UserDao;
import com.elizabeth.exhibition.entity.UserEntity;

import java.util.Optional;

public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {
    public static final String WITH_ID_QUERY = "from UserEntity where id = ?1";
    public static final String FIND_BY_EMAIL_QUERY = "from UserEntity where email = ?1";

    public Optional<UserEntity> findById(long id) {
        return super.findById(id, WITH_ID_QUERY);
    }

    public void delete(long id) {
        super.delete(id, WITH_ID_QUERY);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return super.findByParam(email, FIND_BY_EMAIL_QUERY);
    }
}
