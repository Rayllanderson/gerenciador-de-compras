package com.rayllanderson.gerenciadordecompras.domain.repositories;

import com.rayllanderson.gerenciadordecompras.domain.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Long> {
    boolean existsByUserId(Long userId);

    void deleteByUserId(Long userId);

    @Query("SELECT i.miniature from Image i where i.user.id = ?1")
    String findMiniature(Long userId);

    @Query("SELECT i.base64 from Image i where i.user.id = ?1")
    String findBase64(Long userId);

}
