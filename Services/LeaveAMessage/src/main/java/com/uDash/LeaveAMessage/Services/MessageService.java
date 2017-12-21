package com.uDash.LeaveAMessage.Services;

import com.uDash.LeaveAMessage.Entities.Message;

import java.util.Collection;

public interface MessageService {
    void save(Message message);
    Collection<Message> allMessages();
}
