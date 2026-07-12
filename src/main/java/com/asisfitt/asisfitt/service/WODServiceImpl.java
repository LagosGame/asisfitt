package com.asisfitt.asisfitt.service;

import com.asisfitt.asisfitt.dto.WODDTO;
import com.asisfitt.asisfitt.dto.WODRequestDTO;
import com.asisfitt.asisfitt.exception.WODNotFoundException;
import com.asisfitt.asisfitt.model.WOD;
import com.asisfitt.asisfitt.repository.WODRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WODServiceImpl implements WODService{

    private final WODRepository wodRepository;

    @Override
    public List<WODDTO> obtenerTodos() {
        return wodRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Cacheable(value = "wods",key = "#id")
    public WODDTO obtenerPorId(Long id) {
        return toDTO(wodRepository.findById(id)
                .orElseThrow(()-> new WODNotFoundException(id)));
    }

    @Override
    public WODDTO crear(WODRequestDTO requestDTO) {
        var wod = WOD.builder()
                .nombre(requestDTO.nombre())
                .descripcion(requestDTO.descripcion())
                .entrenamiento(requestDTO.entrenamiento())
                .dificultad(requestDTO.dificultad())
                .build();
        return toDTO(wodRepository.save(wod));
    }

    @Override
    @CacheEvict(value = "wods",key = "#id")
    public WODDTO actualizar(Long id, WODRequestDTO requestDTO) {
        var existente = wodRepository.findById(id)
                .orElseThrow(()-> new WODNotFoundException(id));
        existente.setNombre(requestDTO.nombre());
        existente.setDescripcion(requestDTO.descripcion());
        existente.setEntrenamiento(requestDTO.entrenamiento());
        existente.setDificultad(requestDTO.dificultad());
        return toDTO(wodRepository.save(existente));
    }

    @Override
    @CacheEvict(value = "wods",key = "#id")
    public void eliminar(Long id) {
        if (!wodRepository.existsById(id)){
            throw new WODNotFoundException(id);
        }
        wodRepository.deleteById(id);
    }
    private WODDTO toDTO(WOD wod){
        return new WODDTO(
                wod.getId(),
                wod.getNombre(),
                wod.getDescripcion(),
                wod.getEntrenamiento(),
                wod.getDificultad()

        );
    }
}
