package com.uDash.WidgetsAggregator.Entities;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name = "registeredWidget")
@Entity
public class WidgetDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    //TODO: add @notBlank validator
    private UUID uuid;
}
