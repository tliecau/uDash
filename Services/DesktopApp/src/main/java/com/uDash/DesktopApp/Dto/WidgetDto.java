package com.uDash.DesktopApp.Dto;

import com.uDash.DesktopApp.Dto.Configurations.TextComponentConfiguration;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class WidgetDto {
    private String name;
    private Long id;
    private List<ComponentDto> components;
    private TextComponentConfiguration textComponentConfiguration;
}
