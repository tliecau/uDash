package com.uDash.DesktopApp.Controllers;

import com.uDash.DesktopApp.Services.WidgetComponentsService;
import com.uDash.Utils.Bussines.WidgetComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestComponentsController {
    @Autowired
    WidgetComponentsService widgetComponentsService;

    @RequestMapping(value = "/components")
    public WidgetComponent components() {
        return widgetComponentsService.getByNumber(0);
    }

}
