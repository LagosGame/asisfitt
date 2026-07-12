package com.asisfitt.asisfitt.dto;

import java.time.LocalDate;

public record ReservaDTO(
        Long id, String claseNombre, String usuarioNombre,LocalDate fecha,String estado
) {
}

