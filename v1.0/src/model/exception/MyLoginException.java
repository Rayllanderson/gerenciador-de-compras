package model.exception;

public class MyLoginException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MyLoginException(String msg) {
        super(msg);
    }

}
