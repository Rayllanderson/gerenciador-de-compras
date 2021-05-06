package com.rayllanderson.gerenciadordecompras.domain.validations;

public class ImageValidation {

    public static boolean isValidImageFile(String contentType) {
        if (contentType == null || contentType.isEmpty()) return false;
        return contentType.equals("image/pjpeg") || contentType.equals("image/jpeg") ||
                contentType.equals("image/png") || contentType.equals("image/bmp") ||
                contentType.equals("image/x-png") || contentType.equals("image/x-icon");
    }
}
