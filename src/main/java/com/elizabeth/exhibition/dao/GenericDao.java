package com.elizabeth.exhibition.dao;

import java.util.Optional;

public interface GenericDao<T> {
    void save(T object);
    Optional<T> findById(long id, String str);
    void update(T object);
    void delete(long id, String str);
}
