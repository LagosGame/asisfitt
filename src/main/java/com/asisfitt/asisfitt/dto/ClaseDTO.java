package com.asisfitt.asisfitt.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ClaseDTO(
        Long id,LocalDate fecha,LocalTime hora, int capacidad,String wodNombre
) {
}
