package com.uDash.DesktopApp.Services;

import com.uDash.Utils.Bussines.WidgetComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class WidgetComponentsService {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    private static final String SERVICE_URL = "http://AGGREGATOR-SERVICE/widgetComponents";

    public WidgetComponentsService() {}

    public WidgetComponent getByNumber(int accountNumber) {
        ResponseEntity<WidgetComponent[]> response = restTemplate.getForEntity( SERVICE_URL, WidgetComponent[].class);

        return Arrays.asList(response.getBody()).get(accountNumber);
    }

    public WidgetComponent getById(UUID id) {
        ResponseEntity<WidgetComponent[]> response = restTemplate.getForEntity( SERVICE_URL, WidgetComponent[].class);

        Optional<WidgetComponent> component = Arrays.asList(response.getBody()).stream().filter(widgetComponent -> widgetComponent.getId().equals(id)).findFirst();
        if (!component.isPresent()) {
            throw new ComponentNotFoundException(id.toString()); // TODO: introduce different error ?
        }
        return component.get();
    }
}
