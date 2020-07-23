package model.exception;

public class ProductoException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    
    public ProductoException (String msg) {
	super(msg);
    }

}