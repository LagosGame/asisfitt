package com.asisfitt.asisfitt.service;
import com.asisfitt.asisfitt.dto.ClaseDTO;
import com.asisfitt.asisfitt.dto.ClaseRequestDTO;

import java.util.List;

public interface ClaseService {
    List<ClaseDTO> obtenerTodos();
    ClaseDTO obtenerPorId(Long id);
    ClaseDTO crear(ClaseRequestDTO requestDTO);
    ClaseDTO actualizar(Long id, ClaseRequestDTO requestDTO);
    void eliminar(Long id);
}

