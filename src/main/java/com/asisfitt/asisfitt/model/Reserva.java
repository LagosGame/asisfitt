package com.asisfitt.asisfitt.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn (name = "clase_id")
    private Clase clase;

    @ManyToOne
    @JoinColumn (name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate fecha;
    @Column(nullable = false)
    private String estado;


}