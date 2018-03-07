package com.uDash.Widget.Controllers;

import com.uDash.Widget.Entities.WidgetDto;
import com.uDash.Widget.Services.WidgetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WidgetsControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WidgetService widgetService;

    @Test
    public void getWidget() throws Exception {
        Long id = 1L;
        WidgetDto widgetDto = prepareWidget(id);
        when(widgetService.getWidgetById(id)).thenReturn(widgetDto);

        mockMvc.perform(get("/widgets/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is(widgetDto.getName())));

        verify(widgetService, times(1)).getWidgetById(id);
        verifyNoMoreInteractions(widgetService);
    }

    @Test
    public void getWidgets() throws Exception {
        List<WidgetDto> widgets = new ArrayList<>();
        widgets.add(prepareWidget(1L));
        widgets.add(prepareWidget(2L));
        when(widgetService.getWidgets()).thenReturn(widgets);

        mockMvc.perform(get("/widgets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("testName")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("testName")));

        verify(widgetService, times(1)).getWidgets();
        verifyNoMoreInteractions(widgetService);
    }

    @Test
    public void deleteWidget() throws Exception {
        Long messageId = 1L;
        prepareWidget(messageId);

        mockMvc.perform(delete("/widgets/1"))
                .andExpect(status().isOk());

        verify(widgetService, times(1)).deleteById(messageId);
    }

    @Test
    public void addWidget() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/widgets");
        request.contentType(APPLICATION_JSON_UTF8);
        request.content("{\"name\" : \"test\" }");

        mockMvc.perform(request)
                .andExpect(status().isOk());

        verify(widgetService, times(1)).addWidget(any());
        verifyNoMoreInteractions(widgetService);
    }

    @Test
    public void updateWidget() throws Exception {
        Long messageId = 1L;
        prepareWidget(messageId);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.patch("/widgets/1");
        request.contentType(APPLICATION_JSON_UTF8);
        request.content("{\"name\" : \"test2\" }");

        mockMvc.perform(request)
                .andExpect(status().isOk());

        verify(widgetService, times(1)).updateWidget(any(), any());
        verifyNoMoreInteractions(widgetService);
    }

    private WidgetDto prepareWidget(Long id) {
        WidgetDto widgetDto = new WidgetDto();
        widgetDto.setId(id);
        widgetDto.setName("testName");

        return widgetDto;
    }

}