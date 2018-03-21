package com.uDash.LeaveAMessage.Controllers;

import com.uDash.Utils.Bussines.WidgetComponent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WidgetInfoController {
    @RequestMapping("/widgetInfo")
    WidgetComponent getWidgets() {
        WidgetComponent widgetComponent = new WidgetComponent();
        widgetComponent.setName("Text Service");
        widgetComponent.setDescription("Place text or text collection in your widgetComponent.");
        widgetComponent.setEntryPoint("/messages");
        widgetComponent.setApplicationName("message-service");
        return widgetComponent;
    }
}
