package com.algaworks.algacomment.comment.service.api.exceptionhandler;

import com.algaworks.algacomment.comment.service.api.client.ModerationClientBadGatewayException;
import com.algaworks.algacomment.comment.service.api.client.ModerationClientNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ModerationClientBadGatewayException.class)
    public ResponseEntity<Void> handle502() {
        return ResponseEntity.status(502).build();
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<Void> handle504() {
        return ResponseEntity.status(504).build();
    }

    @ExceptionHandler(ModerationClientNotFoundException.class)
    public ResponseEntity<Void> handle404() {
        return ResponseEntity.status(404).build();
    }
}