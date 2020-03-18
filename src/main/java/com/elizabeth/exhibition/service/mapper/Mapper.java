package com.elizabeth.exhibition.service.mapper;

public interface Mapper<E, D> {
    D mapEntityToDomain(E entity);
    E mapDomainToEntity(D item);
}
