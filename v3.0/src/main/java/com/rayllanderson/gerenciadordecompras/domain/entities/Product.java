package com.rayllanderson.gerenciadordecompras.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 1, max = 50)
    private String name;

    @Size(max = 999999)
    private BigDecimal stipulatedPrice;

    @Size(max = 999999)
    private BigDecimal spentPrice;

    private Boolean purchased;

    @ManyToOne
    private Category category;
}
