package com.uDash.Utils.Bussines;

import com.uDash.DesktopApp.Dto.ComponentId;
import lombok.Data;

@Data
public class WidgetComponent {
    private ComponentId id; //TODO : introduce system to set this dynamicly
    private String name;
    private String description;
    private String entryPoint;
}
