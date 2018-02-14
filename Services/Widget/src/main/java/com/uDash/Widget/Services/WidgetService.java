package com.uDash.Widget.Services;

import com.uDash.Widget.Entities.WidgetDto;
import com.uDash.Widget.Entities.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WidgetService {
    @Autowired
    private WidgetRepository messageRepository;

    public WidgetDto getWidgetById(Long widgetId) {
        return messageRepository.getById(widgetId);
    }

    public List<WidgetDto> getWidgets() {
        List<WidgetDto> widgets = messageRepository.findAll();
        return widgets;
    }

    public void deleteById(Long messageId) {
        messageRepository.delete(messageId);
    }

    public void addWidget(WidgetDto widgetDto) {
        messageRepository.save(widgetDto);
    }
}
