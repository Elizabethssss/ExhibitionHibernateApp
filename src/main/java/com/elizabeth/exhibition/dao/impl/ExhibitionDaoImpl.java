package com.elizabeth.exhibition.dao.impl;


import com.elizabeth.exhibition.dao.ExhibitionDao;
import com.elizabeth.exhibition.dao.Page;
import com.elizabeth.exhibition.entity.ExhibitionEntity;

import java.util.List;
import java.util.Optional;

public class ExhibitionDaoImpl extends AbstractDao<ExhibitionEntity> implements ExhibitionDao {
    public static final String WITH_ID_QUERY = "from ExhibitionEntity where id = ?1";
    public static final String FIND_BY_DATE_LIKE_QUERY = "from ExhibitionEntity where date_from LIKE ?1";

    public Optional<ExhibitionEntity> findById(long id) {
        return super.findById(id, WITH_ID_QUERY);
    }

    public void delete(long id) {
        super.delete(id, WITH_ID_QUERY);
    }

    @Override
    public List<ExhibitionEntity> findByDateLike(String date, Page page) {
        return super.findAll(date, page, FIND_BY_DATE_LIKE_QUERY);
    }
}
