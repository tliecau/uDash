package com.uDash.DesktopApp.Dto;

import com.uDash.DesktopApp.Dto.Configurations.TextComponentConfiguration;
import lombok.Data;

import java.util.List;

@Data
public class WidgetDto {
    private String name;
    private Long id;
    private List<ComponentId> components;
    private TextComponentConfiguration textComponentConfiguration;
}
