package com.uDash.Widget.Controllers;

import com.uDash.Widget.Entities.WidgetDto;
import com.uDash.Widget.Services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class WidgetsController {
    @Autowired
    private WidgetService widgetService;

    @RequestMapping("/widgets/{messageId}")
    WidgetDto getWidget(@PathVariable Long widgetId) {
        WidgetDto messageById = widgetService.getWidgetById(widgetId);
        return messageById;
    }

    @RequestMapping("/widgets")
    List<WidgetDto> getWidgets() {
        List<WidgetDto> messagesById = widgetService.getWidgets();
        return messagesById;
    }

    @RequestMapping(method = DELETE, value = "/widgets/{messageId}")
    void deleteWidget(@PathVariable Long messageId) {
        widgetService.deleteById(messageId);
    }

    @RequestMapping(method = POST, value = "/widgets")
    void addWidget(WidgetDto widgetDto) {
        widgetService.addWidget(widgetDto);
    }
}
