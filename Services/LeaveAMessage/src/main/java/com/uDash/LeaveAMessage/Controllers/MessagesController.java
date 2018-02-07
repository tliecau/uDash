package com.uDash.LeaveAMessage.Controllers;

import com.uDash.LeaveAMessage.Entities.Message;
import com.uDash.LeaveAMessage.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;

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

    @RequestMapping("/messages")
    Collection<Message> messages() {
        return messageService.allMessages();
    }

    @RequestMapping(method = POST, value = "/messages")
    void addMessage(@RequestBody @Valid Message message) {
        messageService.save(message);
    }

    @RequestMapping(method = OPTIONS, value = "/messages")
    ResponseEntity getMessagesOptions(HttpServletResponse response) {
        response.setHeader("Allow", "HEAD,GET,PUT,OPTIONS"); //TODO Automate this
        return new ResponseEntity(HttpStatus.OK);
    }


    //TODO Add paging

}
