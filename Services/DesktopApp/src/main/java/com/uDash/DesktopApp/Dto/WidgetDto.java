package com.uDash.DesktopApp.Dto;

import com.uDash.DesktopApp.Dto.Configurations.TextComponentConfiguration;
import lombok.Data;

import java.util.List;

@Data
public class WidgetDto {
    private String name;
    private List<ComponentId> components;
    private TextComponentConfiguration textComponentConfiguration;
}
