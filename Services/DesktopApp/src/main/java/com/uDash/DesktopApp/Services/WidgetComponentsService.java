package com.uDash.DesktopApp.Services;

import com.uDash.Utils.Bussines.WidgetComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

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
}
