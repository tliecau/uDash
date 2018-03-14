package com.uDash.WidgetsAggregator.Entities;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface WidgetRepository extends CrudRepository<WidgetDto, Long> {
    List<WidgetDto> findByName(String name);

    List<WidgetDto> findById(UUID id);
}