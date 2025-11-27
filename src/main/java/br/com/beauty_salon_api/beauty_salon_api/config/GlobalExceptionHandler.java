package br.com.beauty_salon_api.beauty_salon_api.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {

    @RestControllerAdvice
    public class ApiExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("erro", ex.getMessage());
            body.put("status", 404);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("erro", ex.getMessage());
            body.put("status", 400);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String msg) {
            super(msg);
        }
    }



}
