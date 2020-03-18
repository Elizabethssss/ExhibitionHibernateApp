package com.elizabeth.exhibition.dao;

import com.elizabeth.exhibition.entity.ExpositionEntity;

import java.util.Optional;

public interface ExpositionDao extends PageableDao<ExpositionEntity> {
    Optional<ExpositionEntity> findByExhibitionId(long exhibitionId);
}
