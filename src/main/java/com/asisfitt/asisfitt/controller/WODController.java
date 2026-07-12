package com.asisfitt.asisfitt.controller;

import com.asisfitt.asisfitt.dto.WODDTO;
import com.asisfitt.asisfitt.dto.WODRequestDTO;
import com.asisfitt.asisfitt.service.WODService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/wods")
@RestController
@RequiredArgsConstructor
public class WODController {
    private final WODService wodService;

    @GetMapping
    public ResponseEntity<List<WODDTO>> obtenerTodos(){
        return ResponseEntity.ok(wodService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WODDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(wodService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<WODDTO> crear(@Valid @RequestBody WODRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(wodService.crear(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WODDTO> actualizar(@Valid @RequestBody WODRequestDTO requestDTO,@PathVariable Long id){
        return ResponseEntity.ok(wodService.actualizar(id,requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar (@PathVariable Long id){
        wodService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
