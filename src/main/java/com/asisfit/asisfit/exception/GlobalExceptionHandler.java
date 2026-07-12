package com.asisfit.asisfit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(UsuarioNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "error","No encontrado",
                "mensaje", ex.getMessage(),
                "timestamp", LocalDateTime.now().toString()
        ));
    }
    @ExceptionHandler(ClaseNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(ClaseNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "error","No encontrado",
                "mensaje", ex.getMessage(),
                "timestamp", LocalDateTime.now().toString()
        ));
    }
    @ExceptionHandler(ReservaNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(ReservaNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "error","No encontrado",
                "mensaje", ex.getMessage(),
                "timestamp", LocalDateTime.now().toString()
        ));
    }
    @ExceptionHandler(WODNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(WODNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "error","No encontrado",
                "mensaje", ex.getMessage(),
                "timestamp", LocalDateTime.now().toString()
        ));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleGeneral(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "error","Error",
                "mensaje", ex.getMessage(),
                "timestamp", LocalDateTime.now().toString()
        ));
    }
}
