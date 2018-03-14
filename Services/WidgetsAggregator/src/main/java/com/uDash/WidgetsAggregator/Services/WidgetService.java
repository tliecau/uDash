package com.uDash.WidgetsAggregator.Services;

import com.uDash.Utils.Bussines.WidgetComponent;
import com.uDash.WidgetsAggregator.Entities.WidgetDto;
import com.uDash.WidgetsAggregator.Entities.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WidgetService {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WidgetRepository widgetRepository;

    public List<WidgetComponent> collectWidgetComponents() {
        List<String> services = this.discoveryClient.getServices();
        List<String> widgetComponentsUris = getWidgetComponentsUris(services);
        List<WidgetComponent> widgetComponent = getWidgetComponentFromUri(widgetComponentsUris);
        mapWithEntity(widgetComponent);

        return widgetComponent;
    }

    private void mapWithEntity(List<WidgetComponent> widgetComponent) {
        widgetComponent.forEach(widget -> {
            WidgetDto widgetEntity = getOrCreateEntity(widget);
            //TODO: ADD MAPPER
            widget.setId(widgetEntity.getUuid());
        });
    }

    private WidgetDto getOrCreateEntity(WidgetComponent widget) {
        String name = widget.getName();
        List<WidgetDto> widgets = widgetRepository.findByName(name);
        Optional<WidgetDto> widgetEntity = widgets.stream().findFirst();
        if (!widgetEntity.isPresent()) {
            widgetEntity = Optional.of(addNew(name));
        }
        return widgetEntity.get();
    }

    private WidgetDto addNew(String name) {
        WidgetDto widgetDto = new WidgetDto();
        widgetDto.setName(name);
        widgetDto.setUuid(UUID.randomUUID());
        widgetRepository.save(widgetDto);
        return widgetDto;
    }

    private List<WidgetComponent> getWidgetComponentFromUri(List<String> widgetComponentsUris) {
        List<WidgetComponent> widgetComponents = new ArrayList<>();
        for (String widgetUri : widgetComponentsUris) {
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

    public WidgetComponent getWidgetComponentById(UUID id) {

        List<WidgetComponent> widgetComponents = collectWidgetComponents();
        return widgetComponents
                .stream()
                .filter(widgetComponent -> widgetComponent.getId().equals(id))
                .findFirst().get();
    }
}
