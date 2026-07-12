package com.asisfit.asisfit.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WOD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private String entrenamiento;
    @Column(nullable = false)
    private String dificultad;
}
