package org.example.restAdapter.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends Exception {
    private HttpStatus status;
    private String message;
    private String messageCode;

    public BusinessException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
}

