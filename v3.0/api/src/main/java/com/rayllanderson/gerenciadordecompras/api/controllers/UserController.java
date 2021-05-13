package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserPutPasswordRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.mapper.UserMapper;
import com.rayllanderson.gerenciadordecompras.domain.model.User;
import com.rayllanderson.gerenciadordecompras.domain.repositories.ImageRepository;
import com.rayllanderson.gerenciadordecompras.domain.repositories.UserRepository;
import com.rayllanderson.gerenciadordecompras.domain.services.ImageService;
import com.rayllanderson.gerenciadordecompras.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<List<UserResponseBody>> findAll() {
        List<UserResponseBody> users =
                userRepository.findAll().stream().map(UserMapper::toUserResponseBody).collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseBody> findById(@PathVariable Long id) {
        return ResponseEntity.ok(UserMapper.toUserResponseBody(userService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponseBody> register(@RequestBody @Valid UserPostRequestBody user) {
        UserResponseBody dto = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/update/password")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid UserPutPasswordRequestBody user,
                                               @AuthenticationPrincipal User authenticatedUser) {
        user.setId(authenticatedUser.getId());
        userService.updatePassword(user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateNameUsernameOrEmail(@RequestBody @Valid UserPutRequestBody user,
                                                          @AuthenticationPrincipal User authenticatedUser) {
        user.setId(authenticatedUser.getId());
        userService.updateNameUsernameOrEmail(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@AuthenticationPrincipal User authenticatedUser) {
        userService.deleteById(authenticatedUser.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/details")
    public ResponseEntity<UserResponseBody> getUserDetails(@AuthenticationPrincipal User authenticatedUser) {
        UserResponseBody user = UserMapper.toUserResponseBody(authenticatedUser);
        user.setBase64(imageRepository.findBase64(user.getId()));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/image")
    public ResponseEntity<Void> uploadImage (@RequestParam MultipartFile file, @AuthenticationPrincipal User authenticatedUser){
        imageService.upload(file, authenticatedUser.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/image")
    public ResponseEntity<Void> deleteImage (@AuthenticationPrincipal User authenticatedUser){
        imageService.delete(authenticatedUser.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/image")
    public ResponseEntity<String> findImage(@AuthenticationPrincipal User authenticatedUser){
        return ResponseEntity.ok(imageRepository.findBase64(authenticatedUser.getId()));
    }

    @GetMapping("/image/miniature")
    public ResponseEntity<String> findMiniature(@AuthenticationPrincipal User authenticatedUser){
        return ResponseEntity.ok(imageRepository.findMiniature(authenticatedUser.getId()));
    }
}
