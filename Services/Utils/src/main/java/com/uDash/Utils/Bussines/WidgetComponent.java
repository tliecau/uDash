package com.uDash.Utils.Bussines;

import lombok.Data;

import java.util.UUID;

@Data
public class WidgetComponent {
    private UUID id; //TODO : introduce system to set this dynamically
    private String name;
    private String description;
    private String entryPoint;
    private String applicationName; //TODO : introduce system to set this dynamically

}
