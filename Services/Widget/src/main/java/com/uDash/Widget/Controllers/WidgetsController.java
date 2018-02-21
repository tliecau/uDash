package com.uDash.Widget.Controllers;

import com.uDash.Widget.Entities.WidgetDto;
import com.uDash.Widget.Services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class WidgetsController {
    @Autowired
    private WidgetService widgetService;

    @RequestMapping(method = GET, value = "/widgets/{widgetId}")
    WidgetDto getWidget(@PathVariable Long widgetId) {
        return widgetService.getWidgetById(widgetId);
    }

    @RequestMapping(method = GET, value = "/widgets")
    List<WidgetDto> getWidgets() {
        return widgetService.getWidgets();
    }

    @RequestMapping(method = DELETE, value = "/widgets/{widgetId}")
    void deleteWidget(@PathVariable Long widgetId) {
        widgetService.deleteById(widgetId);
    }

    @RequestMapping(method = POST, value = "/widgets")
    void addWidget(@RequestBody WidgetDto widgetDto) {
        widgetService.addWidget(widgetDto);
    }
}
