package model.exception;

public class CategoriaException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    
    public CategoriaException (String msg) {
	super(msg);
    }

}
