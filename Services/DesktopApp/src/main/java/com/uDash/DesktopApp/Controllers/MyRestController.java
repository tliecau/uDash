package com.uDash.DesktopApp.Controllers;

import com.uDash.DesktopApp.Services.WidgetService;
import com.uDash.Utils.Bussines.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    WidgetService widgetService;

    @RequestMapping(value = "/widget")
    public Widget witdget() {
        return widgetService.getByNumber(1);
    }
}
