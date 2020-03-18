package com.elizabeth.exhibition.service.impl;


import com.elizabeth.exhibition.dao.ExpositionDao;
import com.elizabeth.exhibition.dao.Page;
import com.elizabeth.exhibition.domain.Exposition;
import com.elizabeth.exhibition.entity.ExpositionEntity;
import com.elizabeth.exhibition.service.ExpositionService;
import com.elizabeth.exhibition.service.mapper.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class ExpositionServiceImpl implements ExpositionService {
    final ExpositionDao expositionDao;
    final Mapper<ExpositionEntity, Exposition> expositionMapper;

    public ExpositionServiceImpl(ExpositionDao expositionDao, Mapper<ExpositionEntity, Exposition> expositionMapper) {
        this.expositionDao = expositionDao;
        this.expositionMapper = expositionMapper;
    }

    @Override
    public List<Exposition> getExpositionsByExhibId(long id, Page page) {
        return expositionDao.findAll(id, page).stream()
                .map(expositionMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long count(Long id) {
        return expositionDao.count(id);
    }
}
