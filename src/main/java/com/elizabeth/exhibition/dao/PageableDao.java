package com.elizabeth.exhibition.dao;

import java.util.List;

public interface PageableDao<T> extends GenericDao<T> {
    List<T> findAll(Long id, Page page);
    long count(Long id);
}
