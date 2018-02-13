package com.uDash.WidgetsAggregator.Services;

import com.uDash.Utils.Bussines.WidgetComponent;
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

    public List<WidgetComponent> collectWidgetComponents() {
        List<String> services = this.discoveryClient.getServices();
        List<String> widgetComponentsUris = getWidgetComponentsUris(services);

        return getWidgetComponentFromUri(widgetComponentsUris);
    }

    private List<WidgetComponent> getWidgetComponentFromUri(List<String> widgetComponentsUris) {
        List<WidgetComponent> widgetComponents = new ArrayList<>();
        for (String widgetUri: widgetComponentsUris) {
            try {
                widgetComponents.add(getWidgetComponentFromUri(widgetUri));
            } catch (WidgetNotFoundException e) {
                e.printStackTrace();
                //TODO : logThis
            }
        }
        return widgetComponents;
    }

    private WidgetComponent getWidgetComponentFromUri(String widgetUri) throws WidgetNotFoundException {
        WidgetComponent widgetComponent = restTemplate.getForObject(widgetUri
                + "/widgetInfo", WidgetComponent.class);

        if (widgetComponent == null) {
            throw new WidgetNotFoundException();
        }

         return widgetComponent;
    }

    private List<String> getWidgetComponentsUris(List<String> services) {
        return services.stream()
                .map(service -> this.discoveryClient.getInstances(service).stream().findFirst().get())
                .filter(serviceInstance -> serviceInstance.getMetadata().get("type").equals("widgetComponent"))
                .map(serviceInstance -> serviceInstance.getUri().toString())
                .collect(Collectors.toList());
    }
}
