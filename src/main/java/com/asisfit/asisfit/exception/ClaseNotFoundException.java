package com.asisfit.asisfit.exception;

public class ClaseNotFoundException extends RuntimeException {
    public ClaseNotFoundException(Long id) {
        super("Clase no encontrada con id : " + id);
    }
}
