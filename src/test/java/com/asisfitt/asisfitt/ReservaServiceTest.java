package com.asisfitt.asisfitt;

import com.asisfitt.asisfitt.dto.ReservaRequestDTO;
import com.asisfitt.asisfitt.model.Clase;
import com.asisfitt.asisfitt.model.Reserva;
import com.asisfitt.asisfitt.model.Usuario;
import com.asisfitt.asisfitt.model.WOD;
import com.asisfitt.asisfitt.repository.ClaseRepository;
import com.asisfitt.asisfitt.repository.ReservaRepository;
import com.asisfitt.asisfitt.repository.UsuarioRepository;
import com.asisfitt.asisfitt.service.ReservaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

        @Mock
        private ReservaRepository reservaRepository;
    @Mock
    private ClaseRepository claseRepository;
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ReservaServiceImpl reservaService;

    private Usuario usuario;
    private Clase clase;
    private WOD wod;
    private Reserva reserva;

    @BeforeEach
    void setUp(){
        wod = WOD.builder()
                .id(1L)
                .nombre("Germán")
                .descripcion("21-15-9 Thrusters y Pull-ups")
                .entrenamiento("For time")
                .dificultad("Alta")
                .build();
        clase = Clase.builder()
                .id(1L)
                .fecha(LocalDate.now())
                .hora(LocalTime.of(7,0))
                .capacidad(15)
                .wod(wod)
                .build();
        usuario = Usuario.builder()
                .id(1L)
                .nombre("Carlos")
                .apellido("Lopez")
                .email("carlos@gmail.com")
                .telefono("666121211")
                .nivel("Intermedio")
                .build();
        reserva = Reserva.builder()
                .id(1L)
                .clase(clase)
                .usuario(usuario)
                .fecha(LocalDate.now())
                .estado("CONFIRMADA")
                .build();
    }

    @Test
    void crear_cuandoUsuarioYaTieneReserva_debeLanzarExcepcion(){
        var request = new ReservaRequestDTO(1L,1L,LocalDate.now());

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(claseRepository.findById(1L)).thenReturn(Optional.of(clase));
        when(reservaRepository.findByUsuarioAndClase(usuario, clase)).thenReturn(List.of(reserva));


        assertThatThrownBy(() -> reservaService.crear(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("El usuario ya tiene esta clase reservada");
    }
    @Test
    void crear_cuandoClaseEstaCompleta_debeLanzarExcepcion(){
        var request = new ReservaRequestDTO(1L,1L,LocalDate.now());
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(claseRepository.findById(1L)).thenReturn(Optional.of(clase));
        when(reservaRepository.findByUsuarioAndClase(usuario, clase)).thenReturn(List.of());
        when(reservaRepository.countByClaseAndEstado(clase,"CONFIRMADA")).thenReturn(15L);

        assertThatThrownBy(()-> reservaService.crear(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("La clase está completa");
    }
    @Test
    void crear_cuandoTodoEsCorrecto_debeGuardarReserva() {
        var request = new ReservaRequestDTO(1L, 1L, LocalDate.now());
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(claseRepository.findById(1L)).thenReturn(Optional.of(clase));
        when(reservaRepository.findByUsuarioAndClase(usuario, clase)).thenReturn(List.of());
        when(reservaRepository.countByClaseAndEstado(clase, "CONFIRMADA")).thenReturn(5L);
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        var resultado = reservaService.crear(request);

        assertThat(resultado.estado()).isEqualTo("CONFIRMADA");
        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }
    @Test
    void cancelar_cuandoReservaExiste_debeCambiarEstado() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        var resultado = reservaService.cancelar(1L);

        assertThat(resultado.estado()).isEqualTo("CANCELADA");
        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }
}
