package com.uDash.LeaveAMessage.Services;

import com.uDash.LeaveAMessage.Entities.MessageRepository;
import com.uDash.LeaveAMessage.Services.Exceptions.MessageNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceImplTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private MessageService messageService;

    @Mock
    MessageRepository messageRepository;

    @Before
    public void setUp() {
        messageService = new MessageServiceImpl(messageRepository);
    }

    @Test
    public void getNotExistingMessageById() throws MessageNotFoundException {
        Long messageId = Long.valueOf(1);
        when(messageRepository.getById(messageId)).thenReturn(null);

        exception.expect(MessageNotFoundException.class);
        messageService.getMessageById(messageId);

        verify(messageRepository, times(1)).getById(messageId);
    }

    @Test
    public void deleteNotExistingId() throws MessageNotFoundException {
        Long messageId = Long.valueOf(1);
        when(messageRepository.getById(messageId)).thenReturn(null);

        exception.expect(MessageNotFoundException.class);
        messageService.deleteById(messageId);

        verify(messageRepository, times(1)).getById(messageId);
    }
}