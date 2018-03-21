package com.uDash.LeaveAMessage.Controllers;

import com.uDash.LeaveAMessage.Entities.Message;
import com.uDash.LeaveAMessage.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MessagesController {
    private final MessageService messageService;

    @Autowired
    public MessagesController(@Qualifier(value = "MessageServiceImpl") MessageService messageRepository) {
        this.messageService = messageRepository;
    }

    @RequestMapping(value = "/messages" , method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Collection<Message>> messages() {
        return new ResponseEntity<>(messageService.allMessages(), HttpStatus.OK);
    }

    @RequestMapping(method = POST, value = "/messages")
    void addMessage(@RequestBody @Valid Message message) {
        messageService.save(message);
    }

    //TODO Add paging
}
