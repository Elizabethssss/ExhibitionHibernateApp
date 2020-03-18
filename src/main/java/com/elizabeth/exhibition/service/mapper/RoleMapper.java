package com.elizabeth.exhibition.service.mapper;

import com.elizabeth.exhibition.domain.Role;
import com.elizabeth.exhibition.entity.RoleEntity;

public class RoleMapper implements Mapper<RoleEntity, Role> {
    @Override
    public Role mapEntityToDomain(RoleEntity entity) {
        return Role.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withDescription(entity.getDescription())
                .build();
    }

    @Override
    public RoleEntity mapDomainToEntity(Role item) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(item.getId());
        roleEntity.setName(item.getName());
        roleEntity.setDescription(item.getDescription());
        return roleEntity;
    }
}
