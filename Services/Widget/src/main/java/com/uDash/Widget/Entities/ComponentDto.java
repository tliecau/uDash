package com.uDash.Widget.Entities;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@Data
@Embeddable
public class ComponentDto {
    // TODO: add @notBlank validator
    @Column(name = "component_id")
    private UUID componentId;
    private String name;
    private String uid;
}
