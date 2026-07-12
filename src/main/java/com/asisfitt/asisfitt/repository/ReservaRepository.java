package com.asisfitt.asisfitt.repository;


import com.asisfitt.asisfitt.model.Clase;
import com.asisfitt.asisfitt.model.Reserva;
import com.asisfitt.asisfitt.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    List<Reserva> findByUsuarioAndClase(Usuario usuario, Clase clase);
    long countByClaseAndEstado(Clase clase, String estado);
}
