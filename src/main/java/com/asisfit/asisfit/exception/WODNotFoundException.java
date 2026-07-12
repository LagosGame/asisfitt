package com.asisfit.asisfit.exception;

public class WODNotFoundException extends RuntimeException {
    public WODNotFoundException(Long id) {
        super("WOD no encontrado con id : " + id);
    }
}
