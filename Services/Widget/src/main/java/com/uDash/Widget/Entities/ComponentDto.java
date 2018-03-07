package com.uDash.Widget.Entities;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class ComponentDto {
    @NotBlank
    @Column(name = "component_id")
    private int componentId;
    private String name;
    private String uid;
}
