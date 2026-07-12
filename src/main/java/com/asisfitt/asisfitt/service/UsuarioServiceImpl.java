package com.asisfitt.asisfitt.service;

import com.asisfitt.asisfitt.dto.UsuarioDTO;
import com.asisfitt.asisfitt.dto.UsuarioRequestDTO;
import com.asisfitt.asisfitt.exception.UsuarioNotFoundException;
import com.asisfitt.asisfitt.model.Usuario;
import com.asisfitt.asisfitt.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioRepository usuarioRepository;


    @Override
    public List<UsuarioDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Cacheable(value = "usuarios",key = "#id")
    public UsuarioDTO obtenerPorId(Long id) {
        return toDTO(usuarioRepository.findById(id)
                .orElseThrow(()-> new UsuarioNotFoundException(id)));
    }

    @Override
    public UsuarioDTO crear(UsuarioRequestDTO requestDTO) {
        var usuario = Usuario.builder()
                .nombre(requestDTO.nombre())
                .apellido(requestDTO.apellido())
                .email(requestDTO.email())
                .telefono(requestDTO.telefono())
                .nivel(requestDTO.nivel())
                .build();
        return toDTO(usuarioRepository.save(usuario));

    }

    @Override
    @CacheEvict(value = "usuarios", key = "#id")
    public UsuarioDTO actualizar(Long id, UsuarioRequestDTO requestDTO) {
        var existente = usuarioRepository.findById(id)
                .orElseThrow(()-> new UsuarioNotFoundException(id));
        existente.setNombre(requestDTO.nombre());
        existente.setApellido(requestDTO.apellido());
        existente.setEmail(requestDTO.email());
        existente.setTelefono(requestDTO.telefono());
        existente.setNivel(requestDTO.nivel());
        return  toDTO(usuarioRepository.save(existente));
    }

    @Override
    @CacheEvict(value = "usuarios", key = "#id")
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)){
            throw new UsuarioNotFoundException(id);
        }
        usuarioRepository.deleteById(id);

    }

    private UsuarioDTO toDTO(Usuario usuario){
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getNivel()
        );
    }
}
