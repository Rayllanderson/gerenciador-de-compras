package com.ray.model.exception;

public class ProdutoException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    
    public ProdutoException (String msg) {
	super(msg);
    }

}