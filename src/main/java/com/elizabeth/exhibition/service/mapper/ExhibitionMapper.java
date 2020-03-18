package com.elizabeth.exhibition.service.mapper;

import com.elizabeth.exhibition.domain.Exhibition;
import com.elizabeth.exhibition.entity.ExhibitionEntity;

public class ExhibitionMapper implements Mapper<ExhibitionEntity, Exhibition> {
    @Override
    public Exhibition mapEntityToDomain(ExhibitionEntity entity) {
        return Exhibition.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withDateFrom(entity.getDateFrom())
                .withDateTo(entity.getDateTo())
                .withTheme(entity.getTheme())
                .withAbout(entity.getAbout())
                .withLongAbout(entity.getLongAbout())
                .withPrice(entity.getPrice())
                .withImage(entity.getImage())
                .build();
    }

    @Override
    public ExhibitionEntity mapDomainToEntity(Exhibition item) {
        ExhibitionEntity exhibitionEntity = new ExhibitionEntity();
        exhibitionEntity.setId(item.getId());
        exhibitionEntity.setName(item.getName());
        exhibitionEntity.setDateFrom(item.getDateFrom());
        exhibitionEntity.setDateTo(item.getDateTo());
        exhibitionEntity.setTheme(item.getTheme());
        exhibitionEntity.setAbout(item.getAbout());
        exhibitionEntity.setLongAbout(item.getLongAbout());
        exhibitionEntity.setPrice(item.getPrice());
        exhibitionEntity.setImage(item.getImage());
        return exhibitionEntity;
    }
}
