package com.uDash.LeaveAMessage.Controllers;

import com.uDash.Utils.Bussines.Widget;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WidgetInfoController {
    @RequestMapping("/widgetInfo")
    Widget getWidgets() {
        Widget widget = new Widget();
        widget.setName("Leave a message");
        widget.setDescription("Leave a message service");
        widget.setEntryPoint("/messages");
        return widget;
    }

}
