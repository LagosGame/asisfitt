package com.asisfit.asisfit.repository;

import com.asisfit.asisfit.model.Clase;
import com.asisfit.asisfit.model.Reserva;
import com.asisfit.asisfit.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    List<Reserva> findByUsuarioAndClase(Usuario usuario, Clase clase);
    long countByClaseAndEstado(Clase clase, String estado);
}
