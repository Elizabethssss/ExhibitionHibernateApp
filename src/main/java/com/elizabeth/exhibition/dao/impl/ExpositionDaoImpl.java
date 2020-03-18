package com.elizabeth.exhibition.dao.impl;


import com.elizabeth.exhibition.dao.ExpositionDao;
import com.elizabeth.exhibition.dao.Page;
import com.elizabeth.exhibition.entity.ExpositionEntity;

import java.util.List;
import java.util.Optional;

public class ExpositionDaoImpl extends AbstractDao<ExpositionEntity> implements ExpositionDao {
    public static final String WITH_ID_QUERY = "from ExpositionEntity where id = ?1";
    public static final String FIND_BY_EXHIBITION_ID_QUERY = "from ExpositionEntity where exhib_id = ?1";
    public static final String FIND_PAGE_BY_EXHIBITION_ID_QUERY = "from ExpositionEntity where exhib_id = ?1";
    public static final String COUNT_ALL_QUERY = "select count(*) from ExpositionEntity where exhib_id = ?1";

    public Optional<ExpositionEntity> findById(long id) {
        return super.findById(id, WITH_ID_QUERY);
    }

    public void delete(long id) {
        super.delete(id, WITH_ID_QUERY);
    }

    @Override
    public Optional<ExpositionEntity> findByExhibitionId(long exhibitionId) {
        return super.findByParam(exhibitionId, FIND_BY_EXHIBITION_ID_QUERY);
    }

    @Override
    public List<ExpositionEntity> findAll(Long id, Page page) {
        return super.findAll(id, page, FIND_PAGE_BY_EXHIBITION_ID_QUERY);
    }

    @Override
    public long count(Long id) {
        return super.count(id, COUNT_ALL_QUERY);
    }
}
