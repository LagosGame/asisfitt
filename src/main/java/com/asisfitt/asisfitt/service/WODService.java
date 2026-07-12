package com.asisfitt.asisfitt.service;


import com.asisfitt.asisfitt.dto.WODDTO;
import com.asisfitt.asisfitt.dto.WODRequestDTO;

import java.util.List;

public interface WODService {
    List<WODDTO> obtenerTodos();
    WODDTO obtenerPorId(Long id);
    WODDTO crear(WODRequestDTO requestDTO);
    WODDTO actualizar(Long id, WODRequestDTO requestDTO);
    void eliminar(Long id);
}

