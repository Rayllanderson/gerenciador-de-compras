package com.rayllanderson.gerenciadordecompras.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadGatewayException extends RuntimeException{

    public BadGatewayException(String message) {
        super(message);
    }
}
