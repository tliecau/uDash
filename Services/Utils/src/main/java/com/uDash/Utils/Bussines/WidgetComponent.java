package com.uDash.Utils.Bussines;

import lombok.Data;

import java.util.UUID;

@Data
public class WidgetComponent {
    private UUID id; //TODO : introduce system to set this dynamicly
    private String name;
    private String description;
    private String entryPoint;
}
