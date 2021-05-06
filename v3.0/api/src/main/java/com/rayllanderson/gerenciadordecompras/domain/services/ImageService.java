package com.rayllanderson.gerenciadordecompras.domain.services;

import com.rayllanderson.gerenciadordecompras.domain.exceptions.BadGatewayException;
import com.rayllanderson.gerenciadordecompras.domain.exceptions.BadRequestException;
import com.rayllanderson.gerenciadordecompras.domain.model.Image;
import com.rayllanderson.gerenciadordecompras.domain.model.User;
import com.rayllanderson.gerenciadordecompras.domain.repositories.ImageRepository;
import com.rayllanderson.gerenciadordecompras.domain.utils.ImageUtil;
import com.rayllanderson.gerenciadordecompras.domain.validations.ImageValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public void upload (MultipartFile file, Long userId) {
        String contentType = file.getContentType();
        boolean isAnImageFile = ImageValidation.isValidImageFile(contentType);
        if (isAnImageFile){
            try {
                String base64 = ImageUtil.getBase64(file);
                Image image = Image.builder()
                        .base64(base64)
                        .contentType(contentType)
                        .miniature(ImageUtil.createMiniature(base64))
                        .user(new User(userId))
                        .build();
                imageRepository.save(image);
            }catch (IOException e){
                throw new BadGatewayException("Ocorreu um erro interno. Por favor, tente novamente. Se o erro persistir, entre em contato.");
            }
        }else {
            throw new BadRequestException("Tipo de arquivo não é válido.");
        }
    }
}
