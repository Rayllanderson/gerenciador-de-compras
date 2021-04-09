package com.rayllanderson.gerenciadordecompras.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 1, max = 50)
    private String name;

    @DecimalMax("999999")
    private BigDecimal budget;

    @JsonIgnore
    @ManyToOne
    private User user;

    public Category(Long id) {
        this.id = id;
    }
}
