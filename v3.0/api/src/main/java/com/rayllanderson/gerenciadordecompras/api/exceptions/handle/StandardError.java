package com.rayllanderson.gerenciadordecompras.api.exceptions.handle;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@SuperBuilder
public class StandardError {
    protected LocalDateTime timestamp;
    protected Integer status;
    protected String title;
    protected Object message;
    protected String path;
}
