package com.asisfitt.asisfitt.service;

import com.asisfitt.asisfitt.dto.ReservaDTO;
import com.asisfitt.asisfitt.dto.ReservaRequestDTO;
import com.asisfitt.asisfitt.exception.ClaseNotFoundException;
import com.asisfitt.asisfitt.exception.ReservaNotFoundException;
import com.asisfitt.asisfitt.exception.UsuarioNotFoundException;
import com.asisfitt.asisfitt.model.Reserva;
import com.asisfitt.asisfitt.repository.ClaseRepository;
import com.asisfitt.asisfitt.repository.ReservaRepository;
import com.asisfitt.asisfitt.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService{

    private final ReservaRepository reservaRepository;
    private final ClaseRepository claseRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<ReservaDTO> obtenerTodos() {
        return reservaRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Cacheable(value = "reservas", key = "#id")
    public ReservaDTO obtenerPorId(Long id) {
        return toDTO(reservaRepository.findById(id)
                .orElseThrow(()-> new ReservaNotFoundException(id)));
    }

    @Override
    public ReservaDTO crear(ReservaRequestDTO requestDTO) {
        var usuario = usuarioRepository.findById(requestDTO.usuarioId())
                .orElseThrow(()-> new UsuarioNotFoundException(requestDTO.usuarioId()));
        var clase = claseRepository.findById(requestDTO.claseId())
                .orElseThrow(()-> new ClaseNotFoundException(requestDTO.claseId()));
        var reservasExistentes = reservaRepository.findByUsuarioAndClase(usuario, clase);
        if (!reservasExistentes.isEmpty()) {
            throw new IllegalStateException("El usuario ya tiene esta clase reservada");
        }
        long reservasConfirmadas = reservaRepository.countByClaseAndEstado(clase, "CONFIRMADA");
        if (reservasConfirmadas >= clase.getCapacidad()) {
            throw new IllegalStateException("La clase está completa");
        }
        var reserva = Reserva.builder()
                .clase(clase)
                .usuario(usuario)
                .fecha(requestDTO.fecha())
                .estado("CONFIRMADA")
                .build();
        return toDTO(reservaRepository.save(reserva));
    }

    @Override
    @CacheEvict(value = "clases", key = "#id")
    public ReservaDTO actualizar(Long id, ReservaRequestDTO requestDTO) {
        var usuario = usuarioRepository.findById(requestDTO.usuarioId())
                .orElseThrow(()-> new UsuarioNotFoundException(requestDTO.usuarioId()));
        var clase = claseRepository.findById(requestDTO.claseId())
                .orElseThrow(()-> new ClaseNotFoundException(requestDTO.claseId()));
        var existente = reservaRepository.findById(id)
                .orElseThrow(()-> new ReservaNotFoundException(id));
        existente.setClase(clase);
        existente.setUsuario(usuario);
        existente.setFecha(requestDTO.fecha());
        return toDTO(reservaRepository.save(existente));
    }

    @Override
    @CacheEvict(value = "clases", key = "#id")
    public void eliminar(Long id) {
        if (!reservaRepository.existsById(id))
        {
            throw new ReservaNotFoundException(id);
        }
        reservaRepository.deleteById(id);

    }

    @Override
    public ReservaDTO cancelar(Long id){
        var existente = reservaRepository.findById(id)
                .orElseThrow(()-> new ReservaNotFoundException(id));
        if (existente.getEstado().equals("CANCELADA"))
        {
            throw new IllegalStateException("La reserva ya está cancelada");
        }
        existente.setEstado("CANCELADA");
        return toDTO(reservaRepository.save(existente));
    }

    private ReservaDTO toDTO(Reserva reserva){
        return new ReservaDTO(
                reserva.getId(),
                reserva.getClase().getWod().getNombre(),
                reserva.getUsuario().getNombre(),
                reserva.getFecha(),
                reserva.getEstado()
        );
    }
}
