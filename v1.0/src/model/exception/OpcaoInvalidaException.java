package model.exception;

public class OpcaoInvalidaException extends Exception{

    private static final long serialVersionUID = 1L;
    
    public OpcaoInvalidaException(String msg){
	super (msg);
    }

}
