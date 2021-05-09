package com.rayllanderson.gerenciadordecompras.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String base64;

    private String contentType;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String miniature;

    @OneToOne
    private User user;
}
