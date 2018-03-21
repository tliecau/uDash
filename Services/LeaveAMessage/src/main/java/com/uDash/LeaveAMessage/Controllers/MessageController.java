package com.uDash.LeaveAMessage.Controllers;

import com.uDash.LeaveAMessage.Entities.Message;
import com.uDash.LeaveAMessage.Services.Exceptions.MessageNotFoundException;
import com.uDash.LeaveAMessage.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
@RequestMapping("/messages/")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/{messageId}")
    Message getMessage(@PathVariable Long messageId) throws MessageNotFoundException {
        Message messageById = messageService.getMessageById(messageId);
        return messageById;
    }

    @RequestMapping(method = DELETE, value = "/{messageId}")
    void deleteMessage(@PathVariable Long messageId) throws MessageNotFoundException {
        messageService.deleteById(messageId);
    }
}
