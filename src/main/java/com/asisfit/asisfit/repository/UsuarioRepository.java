package com.asisfit.asisfit.repository;

import com.asisfit.asisfit.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario,Long> {

}
