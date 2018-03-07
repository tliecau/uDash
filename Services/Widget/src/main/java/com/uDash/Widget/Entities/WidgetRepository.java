package com.uDash.Widget.Entities;

import org.springframework.data.repository.CrudRepository;

public interface WidgetRepository extends CrudRepository<WidgetDto, Long> {
    WidgetDto getById(Long messageId);
}
