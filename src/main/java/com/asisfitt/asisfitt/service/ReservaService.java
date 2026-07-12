package com.asisfitt.asisfitt.service;

import com.asisfitt.asisfitt.dto.ReservaDTO;
import com.asisfitt.asisfitt.dto.ReservaRequestDTO;

import java.util.List;

public interface ReservaService {
    List<ReservaDTO> obtenerTodos();
    ReservaDTO obtenerPorId(Long id);
    ReservaDTO crear(ReservaRequestDTO requestDTO);
    ReservaDTO actualizar(Long id, ReservaRequestDTO requestDTO);
    void eliminar(Long id);
    ReservaDTO cancelar(Long id);
}