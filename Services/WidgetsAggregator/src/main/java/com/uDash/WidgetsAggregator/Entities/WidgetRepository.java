package com.uDash.WidgetsAggregator.Entities;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WidgetRepository extends CrudRepository<WidgetDto, Long> {
    List<WidgetDto> findByName(String name);
}