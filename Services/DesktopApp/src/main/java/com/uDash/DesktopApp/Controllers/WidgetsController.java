package com.uDash.DesktopApp.Controllers;

import com.uDash.DesktopApp.Dto.WidgetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
public class WidgetsController {
    @RequestMapping(method = POST, value = "/widgets")
    public ResponseEntity addWitdget(@RequestBody @Valid WidgetDto widget) {
        // TODO : finish this
        return ResponseEntity.ok(null);
    }
}
