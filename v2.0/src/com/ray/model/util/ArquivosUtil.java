package com.ray.model.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import com.ray.model.exception.EntradaInvalidaException;

public class ArquivosUtil {

    /**
     * Converte a imagem em base 64, converte em PNG e então inicia o processo de
     * criação de miniatura
     * 
     * @param request
     * @return imagem em forma de miniatura
     * @throws IOException
     * @throws ServletException
     * @throws EntradaInvalidaException tipo de arquivo inválido
     */
    public static String createMiniatureBase64(HttpServletRequest request)
	    throws IOException, ServletException, EntradaInvalidaException {
	if (ServletFileUpload.isMultipartContent(request)) {// validando de form é de upload
	    Part imagem = request.getPart("file");
	    if (imagem != null && imagem.getInputStream().available() > 0) { // siginifica que tem arquivo!
		if (imagem.getContentType().contains("image")) {
		    // convertendo pra base64
		    String fotoBase64 = Base64.encodeBase64String(streamToByte(imagem.getInputStream()));

		    /* Transforma emum bufferedImage */
		    byte[] imageByteDecode = Base64.decodeBase64(fotoBase64);
		    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));

		    /* Pega o tipo da imagem */
		    int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

		    /* Cria imagem em miniatura */
		    BufferedImage resizedImage = new BufferedImage(100, 100, type);
		    Graphics2D g = resizedImage.createGraphics();
		    g.drawImage(bufferedImage, 0, 0, 100, 100, null);
		    g.dispose();

		    /* Escrever imagem novamente */
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ImageIO.write(resizedImage, "png", baos);
		    return "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
		} else
		    throw new EntradaInvalidaException("Tipo de arquivo inválido");
	    }
	}
	return ""; //sem arquivos
    }

    /**
     * retorna a base 64 do arquivo
     * 
     * @param request
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws EntradaInvalidaException tipo de arquivo inválido
     */
    public static String createBase64(HttpServletRequest request)
	    throws IOException, ServletException, EntradaInvalidaException {
	if (ServletFileUpload.isMultipartContent(request)) {
	    Part imagem = request.getPart("file");
	    if (imagem.getSize() > 0) {
		if (imagem.getContentType().contains("image")) {
		return "data:" + imagem.getContentType() + ";base64,"
			+ Base64.encodeBase64String(streamToByte(imagem.getInputStream()));
		} else
		    throw new EntradaInvalidaException("Tipo de arquivo inválido");
	    }
	}
	return ""; //sem arquivos
    }

    private static byte[] streamToByte(InputStream imagem) throws IOException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	int reads = imagem.read();
	while (reads != -1) {
	    baos.write(reads);
	    reads = imagem.read();
	}
	return baos.toByteArray();
    }
}
