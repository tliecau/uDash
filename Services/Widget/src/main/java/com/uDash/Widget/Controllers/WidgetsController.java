package com.uDash.Widget.Controllers;

import com.uDash.Widget.Entities.WidgetDto;
import com.uDash.Widget.Services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class WidgetsController {
    @Autowired
    private WidgetService widgetService;

    @RequestMapping(method = GET, value = "/widgets")
    Iterable<WidgetDto> getWidgets() {
        return widgetService.getWidgets();
    }

    @RequestMapping(method = GET, value = "/widgets/{widgetId}")
    WidgetDto getWidget(@PathVariable Long widgetId) {
        return widgetService.getWidgetById(widgetId);
    }

    @RequestMapping(method = DELETE, value = "/widgets/{widgetId}")
    void deleteWidget(@PathVariable Long widgetId) {
        widgetService.deleteById(widgetId);
    }

    @RequestMapping(method = PATCH, value = "/widgets/{widgetId}")
    void updateWidget(@PathVariable Long widgetId, @RequestBody WidgetDto widgetDto) {
        widgetService.updateWidget(widgetId, widgetDto);
    }

    @RequestMapping(method = POST, value = "/widgets")
    void addWidget(@RequestBody WidgetDto widgetDto) {
        widgetService.addWidget(widgetDto);
    }
}
