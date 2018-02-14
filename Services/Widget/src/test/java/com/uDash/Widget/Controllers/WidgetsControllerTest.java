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

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    }

    @Test
    public void deleteWidget() throws Exception {
    }

    @Test
    public void addWidget() throws Exception {
    }

    private WidgetDto prepareWidget(Long id) {
        WidgetDto widgetDto = new WidgetDto();
        widgetDto.setId(id);
        widgetDto.setName("testName");

        return widgetDto;
    }

}