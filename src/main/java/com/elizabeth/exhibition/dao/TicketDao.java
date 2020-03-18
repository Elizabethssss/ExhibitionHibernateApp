package com.elizabeth.exhibition.dao;

import com.elizabeth.exhibition.entity.TicketEntity;

import java.util.List;

public interface TicketDao extends PageableDao<TicketEntity> {
    List<TicketEntity> findByUserIdAndIsBought(Long userId, boolean isBought);
}
