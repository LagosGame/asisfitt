package com.asisfitt.asisfitt.controller;

import com.asisfitt.asisfitt.dto.ReservaDTO;
import com.asisfitt.asisfitt.dto.ReservaRequestDTO;
import com.asisfitt.asisfitt.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/reservas")
@RestController
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> obtenerTodos(){
        return ResponseEntity.ok(reservaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(reservaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> crear(@Valid @RequestBody ReservaRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.crear(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> actualizar(@Valid @RequestBody ReservaRequestDTO requestDTO,@PathVariable Long id){
        return ResponseEntity.ok(reservaService.actualizar(id,requestDTO));
    }
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ReservaDTO> cancelar (@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.cancelar(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar (@PathVariable Long id){
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}