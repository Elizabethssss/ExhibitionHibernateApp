package com.elizabeth.exhibition.service.impl;


import com.elizabeth.exhibition.dao.Page;
import com.elizabeth.exhibition.dao.impl.ExhibitionDaoImpl;
import com.elizabeth.exhibition.domain.Exhibition;
import com.elizabeth.exhibition.entity.ExhibitionEntity;
import com.elizabeth.exhibition.service.ExhibitionService;
import com.elizabeth.exhibition.service.mapper.Mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExhibitionServiceImpl implements ExhibitionService {
    private final ExhibitionDaoImpl exhibitionDao;
    private final Mapper<ExhibitionEntity, Exhibition> exhibitionMapper;

    public ExhibitionServiceImpl(ExhibitionDaoImpl exhibitionDao,
                                 Mapper<ExhibitionEntity, Exhibition> exhibitionMapper) {
        this.exhibitionDao = exhibitionDao;
        this.exhibitionMapper = exhibitionMapper;
    }

    @Override
    public Optional<Exhibition> getExhibitionById(long id) {
        return exhibitionDao.findById(id).map(exhibitionMapper::mapEntityToDomain);
    }

    @Override
    public Map<Integer, List<Exhibition>> getMonthExhibitionsMap() {
        Map<Integer, List<Exhibition>> result = new LinkedHashMap<>();
        for (int i = 1; i <= 12; i++) {
            List<Exhibition> temp = exhibitionDao.findByDateLike("%-%" + i + "-%", new Page(0, 100)).stream()
                    .map(exhibitionMapper::mapEntityToDomain).collect(Collectors.toList());
            result.put(i-1, temp);
        }
        return result;
    }
}
