package com.uDash.DesktopApp.Services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ComponentNotFoundException extends RuntimeException {

    ComponentNotFoundException(String userId) {
        super("could not find component '" + userId + "'.");
    }
}
