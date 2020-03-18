package com.elizabeth.exhibition.service;


import com.elizabeth.exhibition.domain.Exhibition;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExhibitionService {
    Optional<Exhibition> getExhibitionById(long id);
    Map<Integer, List<Exhibition>> getMonthExhibitionsMap();
}
