package com.asisfitt.asisfitt.service;

import com.asisfitt.asisfitt.dto.ClaseDTO;
import com.asisfitt.asisfitt.dto.ClaseRequestDTO;
import com.asisfitt.asisfitt.exception.ClaseNotFoundException;
import com.asisfitt.asisfitt.exception.WODNotFoundException;
import com.asisfitt.asisfitt.model.Clase;
import com.asisfitt.asisfitt.repository.ClaseRepository;
import com.asisfitt.asisfitt.repository.WODRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ClaseServiceImpl implements ClaseService{


    private final ClaseRepository claseRepository;
    private final WODRepository wodRepository;

    @Override
    public List<ClaseDTO> obtenerTodos() {
        return claseRepository.findAll().stream()
                .map(this::toDTO)
                .toList()
                ;
    }

    @Override
    @Cacheable(value = "clases",key = "#id")
    public ClaseDTO obtenerPorId(Long id) {
        return toDTO(claseRepository.findById(id)
                .orElseThrow(()-> new ClaseNotFoundException(id)));
    }

    @Override

    public ClaseDTO crear(ClaseRequestDTO requestDTO) {
        var wod = wodRepository.findById(requestDTO.wodId())
                .orElseThrow(()-> new ClaseNotFoundException(requestDTO.wodId()));
        var clase = Clase.builder()
                .fecha(requestDTO.fecha())
                .hora(requestDTO.hora())
                .capacidad(requestDTO.capacidad())
                .wod(wod)
                .build();
        return toDTO(claseRepository.save(clase));
    }

    @Override
    @CacheEvict(value = "clases", key = "#id")
    public ClaseDTO actualizar(Long id, ClaseRequestDTO requestDTO) {
        var wod = wodRepository.findById(requestDTO.wodId())
                .orElseThrow(()-> new WODNotFoundException(requestDTO.wodId()));
        var existente = claseRepository.findById(id)
                .orElseThrow(()-> new ClaseNotFoundException(id));
        existente.setFecha(requestDTO.fecha());
        existente.setHora(requestDTO.hora());
        existente.setCapacidad(requestDTO.capacidad());
        existente.setWod(wod);
        return toDTO(claseRepository.save(existente));
    }

    @Override
    @CacheEvict(value = "clases", key = "#id")
    public void eliminar(Long id) {
        if (!claseRepository.existsById(id)){
            throw new ClaseNotFoundException(id);
        }
        claseRepository.deleteById(id);
    }

    private ClaseDTO toDTO(Clase clase){
        return new ClaseDTO(
                clase.getId(),
                clase.getFecha(),
                clase.getHora(),
                clase.getCapacidad(),
                clase.getWod().getNombre()
        );
    }
}

