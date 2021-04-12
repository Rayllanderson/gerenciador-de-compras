package com.rayllanderson.gerenciadordecompras.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Mensagem padrão de error: Objeto não encontrado
     */
    public BadRequestException() {
        super("Objeto não encontrado");
    }
}
