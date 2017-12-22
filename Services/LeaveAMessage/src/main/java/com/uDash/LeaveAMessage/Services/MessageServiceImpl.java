package com.uDash.LeaveAMessage.Services;

import com.uDash.LeaveAMessage.Entities.Message;
import com.uDash.LeaveAMessage.Entities.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    @Transactional
    public Collection<Message> allMessages() {
        return messageRepository.findAll();
    }
}
