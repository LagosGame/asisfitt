package com.asisfit.asisfit.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public record ClaseRequestDTO(
        @NotNull(message = "La fecha no puede estar vacía.")
        LocalDate fecha,
        @NotNull(message = "La hora no puede estar vacía.")
        LocalTime hora,
        @NotNull(message = "La capacidad no puede estar vacía.")
        @Min(value = 1)
        @Max(value = 20)
        int capacidad,
        @NotNull(message = "El nombre del wod no puede estar vacío.")
        Long wodId
) {
}
