package com.uDash.DesktopApp.Services;

import com.uDash.Utils.Bussines.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class WidgetService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;
    private String serviceUrl;

    public WidgetService() {
        this.serviceUrl = "http://AGGREGATOR-SERVICE";
    }

    public Widget getByNumber(int accountNumber) {
        ResponseEntity<Widget[]> response = restTemplate.getForEntity( "http://AGGREGATOR-SERVICE/widgets", Widget[].class);

        return Arrays.asList(response.getBody()).get(0);
    }
}
