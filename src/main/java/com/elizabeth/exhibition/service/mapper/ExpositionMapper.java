package com.elizabeth.exhibition.service.mapper;

import com.elizabeth.exhibition.domain.Exposition;
import com.elizabeth.exhibition.entity.ExpositionEntity;

public class ExpositionMapper implements Mapper<ExpositionEntity, Exposition> {
    @Override
    public Exposition mapEntityToDomain(ExpositionEntity entity) {
        return Exposition.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withAbout(entity.getAbout())
                .withImage(entity.getImage())

                .build();
    }

    @Override
    public ExpositionEntity mapDomainToEntity(Exposition item) {
        ExpositionEntity expositionEntity = new ExpositionEntity();
        expositionEntity.setId(item.getId());
        expositionEntity.setName(item.getName());
        expositionEntity.setAbout(item.getAbout());
        expositionEntity.setImage(item.getImage());
        return expositionEntity;
    }
}
