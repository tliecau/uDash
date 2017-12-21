package com.uDash.LeaveAMessage.Controlers;

import com.uDash.LeaveAMessage.Entities.Message;
import com.uDash.LeaveAMessage.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MessageControler {
    private final MessageService messageService;

    @Autowired
    public MessageControler(MessageService messageRepository) {
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
