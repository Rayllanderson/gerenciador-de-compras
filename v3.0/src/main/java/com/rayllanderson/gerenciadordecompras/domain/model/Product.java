package com.rayllanderson.gerenciadordecompras.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 1, max = 50)
    private String name;

    @DecimalMax("999999")
    private BigDecimal stipulatedPrice;

    @DecimalMax("999999")
    private BigDecimal spentPrice;

    private Boolean purchased;

    @JsonIgnore
    @ManyToOne
    private Category category;
}
