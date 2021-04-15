package com.ray.model.exception;

public class CategoriaInexistenteException extends Exception {

    private static final long serialVersionUID = 1L;

    public CategoriaInexistenteException(String msg) {
        super(msg);
    }

}
