package com.uDash.WidgetsAggregator.Entities;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;

@Data
@Table(name = "registratedWidget")
@Entity
public class WidgetDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String uuid;
}
