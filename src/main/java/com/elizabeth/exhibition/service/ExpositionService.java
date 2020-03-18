package com.elizabeth.exhibition.service;

import com.elizabeth.exhibition.dao.Page;
import com.elizabeth.exhibition.domain.Exposition;

import java.util.List;

public interface ExpositionService {
    List<Exposition> getExpositionsByExhibId(long id, Page page);
    long count(Long id);
}
