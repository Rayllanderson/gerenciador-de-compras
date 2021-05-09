package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.domain.model.User;
import com.rayllanderson.gerenciadordecompras.domain.repositories.ImageRepository;
import com.rayllanderson.gerenciadordecompras.domain.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/image")
public class ImageController {

    private final ImageService imageService;
    private final ImageRepository imageRepository;

    @PostMapping
    public ResponseEntity<Void> upload (@RequestParam MultipartFile file, @AuthenticationPrincipal User userAuthenticated){
        imageService.upload(file, userAuthenticated.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete (@AuthenticationPrincipal User userAuthenticated){
        imageService.delete(userAuthenticated.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<String> findBase64(@AuthenticationPrincipal User userAuthenticated){
        return ResponseEntity.ok(imageRepository.findBase64(userAuthenticated.getId()));
    }

    @GetMapping("/miniature")
    public ResponseEntity<String> findMiniature(@AuthenticationPrincipal User userAuthenticated){
        return ResponseEntity.ok(imageRepository.findMiniature(userAuthenticated.getId()));
    }
}
