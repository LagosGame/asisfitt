package com.asisfitt.asisfitt.controller;

import com.asisfitt.asisfitt.dto.ClaseDTO;
import com.asisfitt.asisfitt.dto.ClaseRequestDTO;
import com.asisfitt.asisfitt.service.ClaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clases")
@RestController
@RequiredArgsConstructor
public class ClaseController {
    private final ClaseService claseService;

    @GetMapping
    public ResponseEntity<List<ClaseDTO>> obtenerTodos(){
        return ResponseEntity.ok(claseService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaseDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(claseService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ClaseDTO> crear(@Valid @RequestBody ClaseRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(claseService.crear(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClaseDTO> actualizar(@Valid @RequestBody ClaseRequestDTO requestDTO,@PathVariable Long id){
        return ResponseEntity.ok(claseService.actualizar(id,requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar (@PathVariable Long id){
        claseService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

