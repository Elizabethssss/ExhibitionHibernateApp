package com.elizabeth.exhibition.dao.impl;


import com.elizabeth.exhibition.dao.Page;
import com.elizabeth.exhibition.dao.TicketDao;
import com.elizabeth.exhibition.entity.TicketEntity;

import java.util.List;
import java.util.Optional;

public class TicketDaoImpl extends AbstractDao<TicketEntity> implements TicketDao {
    public static final String WITH_ID_QUERY = "from TicketEntity where id = ?1";
    public static final String FIND_BY_USER_ID_AND_IS_BOUGHT_QUERY = "from TicketEntity where user_id = ?1 and is_bought = ?2";
    public static final String FIND_PAGE_BY_USER_ID_QUERY = "from TicketEntity where user_id = ?1 AND is_bought = 1";
    public static final String COUNT_ALL_QUERY = "select count(*) from TicketEntity WHERE user_id = ?1 and is_bought = 1";

    public Optional<TicketEntity> findById(long id) {
        return super.findById(id, WITH_ID_QUERY);
    }

    public void delete(long id) {
        super.delete(id, WITH_ID_QUERY);
    }

    @Override
    public List<TicketEntity> findByUserIdAndIsBought(Long userId, boolean isBought) {
        return super.findByParams(userId, isBought, FIND_BY_USER_ID_AND_IS_BOUGHT_QUERY);
    }

    @Override
    public List<TicketEntity> findAll(Long id, Page page) {
        return super.findAll(id, page, FIND_PAGE_BY_USER_ID_QUERY);
    }

    @Override
    public long count(Long id) {
        return super.count(id, COUNT_ALL_QUERY);
    }
}
