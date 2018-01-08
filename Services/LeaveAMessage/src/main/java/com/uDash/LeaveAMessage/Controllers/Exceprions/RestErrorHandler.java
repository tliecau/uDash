package com.uDash.LeaveAMessage.Controllers.Exceprions;

import com.uDash.LeaveAMessage.Services.Exceptions.MessageNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestErrorHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);

    @ExceptionHandler(MessageNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cannot find message")
    public void handleTodoNotFoundException(MessageNotFoundException ex) {
        LOGGER.debug("Handling 404 error on a todo entry");
    }
}
