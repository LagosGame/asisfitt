package com.asisfitt.asisfitt.controller;

import com.asisfitt.asisfitt.dto.UsuarioDTO;
import com.asisfitt.asisfitt.dto.UsuarioRequestDTO;
import com.asisfitt.asisfitt.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/usuarios")
@RestController
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerTodos(){
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@Valid @RequestBody UsuarioRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@Valid @RequestBody UsuarioRequestDTO requestDTO,@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.actualizar(id,requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar (@PathVariable Long id){
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

