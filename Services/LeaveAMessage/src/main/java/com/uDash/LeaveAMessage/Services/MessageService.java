package com.uDash.LeaveAMessage.Services;

import com.uDash.LeaveAMessage.Entities.Message;
import com.uDash.LeaveAMessage.Services.Exceptions.MessageNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface MessageService {
    void save(Message message);
    void deleteById(Long messageId) throws MessageNotFoundException;
    Message getMessageById(Long messageId);
    Collection<Message> allMessages();
}
