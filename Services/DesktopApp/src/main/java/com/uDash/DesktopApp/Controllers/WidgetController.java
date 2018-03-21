package com.uDash.DesktopApp.Controllers;

import com.google.gson.JsonObject;
import com.uDash.DesktopApp.Dto.WidgetDto;
import com.uDash.DesktopApp.Services.ComponentService;
import com.uDash.DesktopApp.Services.WidgetService;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("api/widgets/{widgetId}")
public class WidgetController {

    @Autowired
    WidgetService widgetService;

    @Autowired
    ComponentService componentService;

    @RequestMapping(method = GET, value = "")
    public WidgetDto getWitdgets(@PathVariable int widgetId) {
        return widgetService.getWidget(widgetId);
    }

    @RequestMapping(method = DELETE, value = "")
    public void deleteWidget(@PathVariable int widgetId) {
        widgetService.deleteWidget(widgetId);
    }

    @RequestMapping(method = GET, value = "/components/{uid}", produces = "application/json")
    public ResponseEntity<String> getComponent(@PathVariable int widgetId, @PathVariable String uid, HttpServletResponse response) throws JSONException {

        UUID componentUid = UUID.fromString(uid);
        WidgetDto widget = widgetService.getWidget(widgetId);

        return new ResponseEntity<>(componentService.get(widget, componentUid), HttpStatus.OK);
    }

    @RequestMapping(method = POST, value = "/components/{uid}", produces = "application/json")
    public ResponseEntity<String> postComponent(@PathVariable int widgetId, @PathVariable String uid, @RequestBody String post) {

        UUID componentUid = UUID.fromString(uid);
        WidgetDto widget = widgetService.getWidget(widgetId);

        return componentService.post(widget, componentUid, post);
    }
}
