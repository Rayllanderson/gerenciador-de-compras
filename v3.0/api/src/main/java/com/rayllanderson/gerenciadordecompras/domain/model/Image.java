package com.rayllanderson.gerenciadordecompras.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String base64;
    private String contentType;
    private String miniature;

    @OneToOne
    private User user;
}
