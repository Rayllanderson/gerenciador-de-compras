package com.rayllanderson.gerenciadordecompras.domain.utils;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageUtil {

    public static String getBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }

    public static String createMiniature(String base64) throws IOException {
        byte[] imageByteDecode = Base64.getDecoder().decode(base64);
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
        BufferedImage cropped = resizeImage(bufferedImage, 320, 320);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(cropped, "png", baos);
        return "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        return Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight,
                Scalr.OP_ANTIALIAS);
    }
}
