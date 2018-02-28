package com.uDash.Widget.Entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class ComponentIdDto {
    @Column(name = "component_id")
    private int id;
}
