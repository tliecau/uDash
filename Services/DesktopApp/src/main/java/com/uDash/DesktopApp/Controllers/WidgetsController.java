package com.uDash.DesktopApp.Controllers;

import com.uDash.DesktopApp.Dto.WidgetDto;
import com.uDash.DesktopApp.Services.WidgetService;
import com.uDash.Utils.Bussines.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/widgets")
public class WidgetsController {

    @Autowired
    WidgetService widgetService;

    @RequestMapping(method = POST, value = "")
    public ResponseEntity addWitdget(@RequestBody @Valid WidgetDto widget) {
        widgetService.addWidget(widget);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(method = GET, value = "")
    public List<WidgetDto> getWitdgets() {
        return widgetService.getWidgets();
    }

 }
