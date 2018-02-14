package com.uDash.DesktopApp.Services;

import com.uDash.DesktopApp.Dto.WidgetDto;
import com.uDash.Utils.Bussines.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WidgetService {
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    private static String WIDGETS_URL = "http://AGGREGATOR-SERVICE/widgets";

    public void addWidget(WidgetDto widget) {
    }

    public List<Widget> getWidgets() {
        List<Widget> widgets = new ArrayList<>();
        Widget widget = new Widget();
        widget.setName("TEST");
        widgets.add(widget);
        return widgets;
    }
}
