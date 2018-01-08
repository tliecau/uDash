package com.uDash.LeaveAMessage.Controllers;

import com.uDash.LeaveAMessage.Entities.Message;
import com.uDash.LeaveAMessage.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MessagesController {
    private final MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageRepository) {
        this.messageService = messageRepository;
    }

    @RequestMapping("/messages")
    Collection<Message> messages() {
        return messageService.allMessages();
    }

    @RequestMapping(method = POST, value = "/messages")
    void addMessage(@RequestBody @Valid Message message) {
        messageService.save(message);
    }
}
