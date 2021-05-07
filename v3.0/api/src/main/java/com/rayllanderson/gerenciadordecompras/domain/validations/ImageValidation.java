package com.rayllanderson.gerenciadordecompras.domain.validations;

public class ImageValidation {

    public static boolean isValidImageFile(String contentType) {
        if (contentType == null || contentType.isEmpty()) return false;
        return contentType.contains("image") && !contentType.contains("gif");
    }
}
