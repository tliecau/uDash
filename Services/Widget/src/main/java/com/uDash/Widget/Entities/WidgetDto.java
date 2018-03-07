package com.uDash.Widget.Entities;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "widget")
@EntityListeners(AuditingEntityListener.class)
public class WidgetDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @ElementCollection
    @CollectionTable(name = "component_identifier", joinColumns = @JoinColumn(name = "widget_id"))
    @OrderColumn(name = "component_order")
    private Set<ComponentDto> components;
}
