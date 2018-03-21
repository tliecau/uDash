package com.uDash.DesktopApp.Services;

import com.google.gson.JsonObject;
import com.uDash.DesktopApp.Dto.ComponentDto;
import com.uDash.DesktopApp.Dto.WidgetDto;
import com.uDash.Utils.Bussines.WidgetComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class ComponentService {

    @Autowired
    WidgetComponentsService widgetComponentsService;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    public ComponentDto getComponent(WidgetDto widget, UUID componentId) {

        Optional<ComponentDto> component = widget.getComponents()
                .stream()
                .filter(componentDto -> componentDto.getComponentId().equals(componentId))
                .findFirst();

        if (!component.isPresent()) {
            throw new ComponentNotFoundException(componentId.toString());
        }
        return component.get();
    }

    public String get(WidgetDto widget, UUID componentUid) {
        ComponentDto component = widget.getComponents().stream()
                .filter(componentDto -> componentDto.getUid()
                        .equals(componentUid))
                .findFirst()
                .get();
        UUID componentId = component.getComponentId();

        WidgetComponent widgetComponent = widgetComponentsService.getById(componentId);

        String url = "http://" + widgetComponent.getApplicationName() + "/" + widgetComponent.getEntryPoint();
        return restTemplate.getForObject(url, String.class);
    }

    public String getPostUrl(WidgetDto widget, UUID componentUid) {
        ComponentDto component = widget.getComponents().stream()
                .filter(componentDto -> componentDto.getUid()
                        .equals(componentUid))
                .findFirst()
                .get();
        UUID componentId = component.getComponentId();

        WidgetComponent widgetComponent = widgetComponentsService.getById(componentId);

        return "http://" + widgetComponent.getApplicationName() + "/" + widgetComponent.getEntryPoint();
    }

    public ResponseEntity<String > post(WidgetDto widget, UUID componentUid, String post) {
        ComponentDto component = widget.getComponents().stream()
                .filter(componentDto -> componentDto.getUid()
                        .equals(componentUid))
                .findFirst()
                .get();
        UUID componentId = component.getComponentId();

        WidgetComponent widgetComponent = widgetComponentsService.getById(componentId);

        String url = "http://" + widgetComponent.getApplicationName() + "/" + widgetComponent.getEntryPoint();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<String> request = new HttpEntity<String>(post, headers);

        return restTemplate.postForEntity(url, request, String.class);
    }
}
