package com.uDash.LeaveAMessage.Services;

import com.uDash.LeaveAMessage.Entities.Message;
import com.uDash.LeaveAMessage.Entities.MessageRepository;
import com.uDash.LeaveAMessage.Services.Exceptions.MessageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

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
    public void deleteById(Long messageId) throws MessageNotFoundException {
        if (messageRepository.getById(messageId) == null) {
            throw new MessageNotFoundException();
        }
        messageRepository.delete(messageId);
    }

    @Override
    @Transactional
    public Message getMessageById(Long messageId) {
        return messageRepository.getById(messageId);
    }

    @Override
    @Transactional
    public Collection<Message> allMessages() {
        return messageRepository.findAll();
    }
}
