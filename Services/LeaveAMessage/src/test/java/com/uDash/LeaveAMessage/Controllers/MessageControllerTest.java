package com.uDash.LeaveAMessage.Controllers;

import com.uDash.LeaveAMessage.Entities.Message;
import com.uDash.LeaveAMessage.Services.Exceptions.MessageNotFoundException;
import com.uDash.LeaveAMessage.Services.MessageService;
import com.uDash.LeaveAMessage.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    public void getMessage() throws Exception {
        Long id = 1L;
        prepareMessage(id);

        mockMvc.perform(get("/messages/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("author", is("testAuthor")))
                .andExpect(jsonPath("message", is("Lorem ipsum")));

        verify(messageService, times(1)).getMessageById(id);
        verifyNoMoreInteractions(messageService);
    }

    @Test
    public void getNotExistingMessage() throws Exception {
        Long messageId = 1L;
        when(messageService.getMessageById(messageId)).thenThrow(new MessageNotFoundException());
        mockMvc.perform(get("/messages/1"))
                .andExpect(status().isNotFound());

        verify(messageService, times(1)).getMessageById(messageId);
        verifyNoMoreInteractions(messageService);
    }

    @Test
    public void deleteMessage() throws Exception {
        Long messageId = 1L;
        prepareMessage(messageId);

        mockMvc.perform(delete("/messages/1"))
                .andExpect(status().isOk());

        verify(messageService, times(1)).deleteById(messageId);
    }
    
    @Test
    public void deleteNotExistingMessage() throws Exception {
        Long messageId = 2L;
        doThrow(new MessageNotFoundException()).when(messageService).deleteById(messageId);

        mockMvc.perform(delete("/messages/2", 1L))
                .andExpect(status().isNotFound());

        verify(messageService, times(1)).deleteById(messageId);
    }

    private Message prepareMessage(Long id) throws MessageNotFoundException {
        Message message = new Message();
        message.setId(id);
        message.setAuthor("testAuthor");
        message.setMessage("Lorem ipsum");
        when(messageService.getMessageById(id)).thenReturn(message);

        return message;
    }
}