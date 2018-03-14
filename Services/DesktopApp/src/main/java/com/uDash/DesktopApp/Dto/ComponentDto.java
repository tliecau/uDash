package com.uDash.DesktopApp.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ComponentDto {
    private UUID componentId;
    private String name;
    private String uid;
}