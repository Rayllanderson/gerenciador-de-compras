package com.ray.model.entities;

public class Arquivo {

    private String arquivoBase64;
    private String contentType;

    public Arquivo(String arquivoBase64, String contentType) {
	super();
	this.arquivoBase64 = arquivoBase64;
	this.contentType = contentType;
    }

    public String getArquivoBase64() {
	return arquivoBase64;
    }

    public void setArquivoBase64(String fotoBase64) {
	this.arquivoBase64 = fotoBase64;
    }

    public String getContentType() {
	return contentType;
    }

    public void setContentType(String contentType) {
	this.contentType = contentType;
    }
}
