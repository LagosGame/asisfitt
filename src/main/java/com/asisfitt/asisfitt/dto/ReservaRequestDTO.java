package com.asisfitt.asisfitt.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;


public record ReservaRequestDTO(
        @NotNull(message = "El id de la clase es obligatorio.")
        Long claseId,
        @NotNull(message = "El id del usuario es obligatorio.")
        Long usuarioId,
        @NotNull(message = "La fecha no puede estar vacía.")
        LocalDate fecha
) {
}