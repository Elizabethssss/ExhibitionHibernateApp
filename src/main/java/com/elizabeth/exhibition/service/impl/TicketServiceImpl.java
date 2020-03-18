package com.elizabeth.exhibition.service.impl;

import com.elizabeth.exhibition.dao.Page;
import com.elizabeth.exhibition.dao.impl.TicketDaoImpl;
import com.elizabeth.exhibition.domain.Ticket;
import com.elizabeth.exhibition.entity.TicketEntity;
import com.elizabeth.exhibition.service.TicketService;
import com.elizabeth.exhibition.service.mapper.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {
    final TicketDaoImpl ticketDao;
    final Mapper<TicketEntity, Ticket> ticketMapper;

    public TicketServiceImpl(TicketDaoImpl ticketDao, Mapper<TicketEntity, Ticket> ticketMapper) {
        this.ticketDao = ticketDao;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public void add(long userId, long exhibitionId) {
        final Ticket temp = Ticket.builder()
//                .withUserId(userId)
//                .withExhibitionId(exhibitionId)
                .withIsBought(false)
                .build();
        final TicketEntity ticket = ticketMapper.mapDomainToEntity(temp);
        ticketDao.save(ticket);
    }

    @Override
    public List<Ticket> getUserExhibsByUserId(long userId, boolean isBought) {
        return ticketDao.findByUserIdAndIsBought(userId, isBought)
                .stream().map(ticketMapper::mapEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteTicketById(long ticketId) {
        ticketDao.delete(ticketId);
    }

    @Override
    public void updateIsBought(long userId) {
        final Ticket temp = Ticket.builder()
//                .withUserId(userId)
                .withIsBought(true)
                .build();
        final TicketEntity ticket = ticketMapper.mapDomainToEntity(temp);
        ticketDao.update(ticket);
    }

    @Override
    public List<Ticket> getPageOfBoughtTickets(long userId, Page page) {
        return ticketDao.findAll(userId, page).stream()
                .map(ticketMapper::mapEntityToDomain).collect(Collectors.toList());
    }
}
