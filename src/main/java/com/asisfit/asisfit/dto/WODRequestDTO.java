package com.asisfit.asisfit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record WODRequestDTO(
        @NotBlank(message = "El nombre no puede estar vacío.")
        @Size(min = 3,max = 100, message = "El mensaje debe contener entre 3 y 100 caracteres.")
        String nombre,
        @NotBlank(message = "La descripcion no puede estar vacío.")
        @Size(min = 3,max = 100, message = "El mensaje debe contener entre 3 y 300 caracteres.")
        String descripcion,
        @NotBlank(message = "El entrenamiento no puede estar vacío.")
        @Size(min = 3,max = 100, message = "El mensaje debe contener entre 3 y 300 caracteres.")
        String entrenamiento,
        @NotBlank(message = "La dificultad no puede estar vacío.")
        @Size(min = 3,max = 50, message = "El mensaje debe contener entre 3 y 100 caracteres.")
        String dificultad
) {
}