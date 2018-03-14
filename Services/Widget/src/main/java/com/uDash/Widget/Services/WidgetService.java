package com.uDash.Widget.Services;

import com.uDash.Widget.Entities.WidgetDto;
import com.uDash.Widget.Entities.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WidgetService {
    @Autowired
    private WidgetRepository messageRepository;

    public WidgetDto getWidgetById(Long widgetId) {
        return messageRepository.getById(widgetId);
    }

    public Iterable<WidgetDto> getWidgets() {
        return messageRepository.findAll();
    }

    public void deleteById(Long messageId) {
        messageRepository.delete(messageId);
    }

    public WidgetDto addWidget(WidgetDto widgetDto) {
        // TODO : add validation if added component is avaiable
        if (widgetDto.getComponents() != null) {
            widgetDto.getComponents().forEach(componentDto -> componentDto.setUid(UUID.randomUUID().toString()));
        }
        return messageRepository.save(widgetDto);
    }

    public WidgetDto updateWidget(Long widgetId, WidgetDto widgetDto) {
        WidgetDto widget = messageRepository.getById(widgetId);
        widgetDto.setId(widget.getId());
        return messageRepository.save(widgetDto);
    }
}
