package com.uDash.Widget.Entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WidgetRepository  extends JpaRepository<WidgetDto, Long> {
    WidgetDto getById(Long messageId);
}
