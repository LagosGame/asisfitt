package com.asisfitt.asisfitt.service;

import com.asisfitt.asisfitt.dto.UsuarioDTO;
import com.asisfitt.asisfitt.dto.UsuarioRequestDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> obtenerTodos();
    UsuarioDTO obtenerPorId(Long id);
    UsuarioDTO crear(UsuarioRequestDTO requestDTO);
    UsuarioDTO actualizar(Long id, UsuarioRequestDTO requestDTO);
    void eliminar(Long id);
}
