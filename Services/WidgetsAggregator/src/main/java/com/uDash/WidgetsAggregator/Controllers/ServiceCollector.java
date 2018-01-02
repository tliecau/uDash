package com.uDash.WidgetsAggregator.Controllers;

import com.uDash.Utils.Bussines.Widget;
import com.uDash.WidgetsAggregator.Services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceCollector {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WidgetService widgetService;

    @RequestMapping("/services/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping("/services")
    public List<String> getServices() {
        return this.discoveryClient.getServices();
    }

    @RequestMapping("/widgets")
    public List<Widget> getWidgets() {
        return widgetService.collectWidgets();
    }
}
