package com.uDash.LeaveAMessage.Entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message getById(Long messageId);
}
