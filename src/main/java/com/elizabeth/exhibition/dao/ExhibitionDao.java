package com.elizabeth.exhibition.dao;

import com.elizabeth.exhibition.entity.ExhibitionEntity;

import java.util.List;

public interface ExhibitionDao extends GenericDao<ExhibitionEntity> {
    List<ExhibitionEntity> findByDateLike(String date, Page page);
}
