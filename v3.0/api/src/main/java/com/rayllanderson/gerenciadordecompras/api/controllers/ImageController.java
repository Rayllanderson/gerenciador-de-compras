package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.api.utils.UserUtil;
import com.rayllanderson.gerenciadordecompras.domain.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/upload")
public class ImageController {

    private final ImageService imageService;
    private final UserUtil myUserUtil;

    @PostMapping
    public ResponseEntity<Void> upload (@RequestParam MultipartFile file, @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        imageService.upload(file, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete (@AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        imageService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
