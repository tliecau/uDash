package com.uDash.WidgetsAggregator.Services;

import com.uDash.Utils.Bussines.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WidgetService {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    public List<Widget> collectWidgets() {
        List<String> services = this.discoveryClient.getServices();
        List<String> widgetsUris = getWidgetsUris(services);

        return getWidgetsFromUri(widgetsUris);
    }

    private List<Widget> getWidgetsFromUri(List<String> widgetsUris) {
        List<Widget> widgets = new ArrayList<>();
        for (String widgetUri: widgetsUris) {
            try {
                widgets.add(getWidgetsFromUri(widgetUri));
            } catch (WidgetNotFoundException e) {
                e.printStackTrace();
                //TODO : logThis
            }
        }
        return widgets;
    }

    private Widget getWidgetsFromUri(String widgetUri) throws WidgetNotFoundException {
        Widget widget = restTemplate.getForObject(widgetUri
                + "/widgetInfo", Widget.class);

        if (widget == null) {
            throw new WidgetNotFoundException();
        }

         return widget;
    }

    private List<String> getWidgetsUris(List<String> services) {
        return services.stream()
                .map(service -> this.discoveryClient.getInstances(service).stream().findFirst().get())
                .filter(serviceInstance -> serviceInstance.getMetadata().get("type").equals("widget"))
                .map(serviceInstance -> serviceInstance.getUri().toString())
                .collect(Collectors.toList());
    }
}
