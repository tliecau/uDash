package com.uDash.DesktopApp.Services;

import com.uDash.DesktopApp.Dto.WidgetDto;
import com.uDash.Utils.Bussines.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WidgetService {
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    private static final String WIDGETS_URL = "http://WIDGET-SERVICE/widgets";

    public WidgetDto addWidget(WidgetDto widget) {
        ResponseEntity<WidgetDto> response = restTemplate.postForEntity(WIDGETS_URL, widget, WidgetDto.class);

        return response.getBody();
    }

    public List<Widget> getWidgets() {
        ResponseEntity<Widget[]> response = restTemplate.getForEntity(WIDGETS_URL, Widget[].class);

        return Arrays.asList(response.getBody());
    }
}
