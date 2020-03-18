package com.elizabeth.exhibition.service.mapper;

import com.elizabeth.exhibition.domain.Ticket;
import com.elizabeth.exhibition.entity.TicketEntity;

import java.util.Optional;

//todo: no entities
public class TicketMapper implements Mapper<TicketEntity, Ticket>{

    @Override
    public Ticket mapEntityToDomain(TicketEntity entity) {
        return Ticket.builder()
                .withId(entity.getId())
                .withUserId(entity.getUserEntity().getId())
                .withExhibitionId(entity.getExhibitionEntity().getId())
                .withIsBought(entity.isBought())
                .build();
    }

    @Override
    public TicketEntity mapDomainToEntity(Ticket item) {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(Optional.ofNullable(item.getId()).orElse(0L));
//        ticketEntity.setUserEntity(item.getUserId());
//        ticketEntity.setExhibitionEntity(Optional.ofNullable(item.getExhibitionId()).orElse(0L));
        ticketEntity.setBought(item.isBought());
        return ticketEntity;
    }
}
