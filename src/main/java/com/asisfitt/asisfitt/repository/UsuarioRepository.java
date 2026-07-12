package com.asisfitt.asisfitt.repository;

import com.asisfitt.asisfitt.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario,Long> {

}
