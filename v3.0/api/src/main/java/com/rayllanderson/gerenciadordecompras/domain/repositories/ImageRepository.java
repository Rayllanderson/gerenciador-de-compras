package com.rayllanderson.gerenciadordecompras.domain.repositories;

import com.rayllanderson.gerenciadordecompras.domain.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
