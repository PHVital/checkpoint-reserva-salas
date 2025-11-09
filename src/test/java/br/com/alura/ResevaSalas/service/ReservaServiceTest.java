package br.com.alura.ResevaSalas.service;

import br.com.alura.ResevaSalas.dto.ReservaRequestDTO;
import br.com.alura.ResevaSalas.excpetion.RegraNegocioException;
import br.com.alura.ResevaSalas.model.Reserva;
import br.com.alura.ResevaSalas.model.Sala;
import br.com.alura.ResevaSalas.model.StatusReserva;
import br.com.alura.ResevaSalas.model.Usuario;
import br.com.alura.ResevaSalas.repository.ReservaRepository;
import br.com.alura.ResevaSalas.repository.SalaRepository;
import br.com.alura.ResevaSalas.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {
    @Mock
    private ReservaRepository reservaRepository;
    @Mock
    private SalaRepository salaRepository;
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ReservaService reservaService;

    private final LocalDateTime inicio = LocalDateTime.now().plusHours(1);
    private final LocalDateTime fim = LocalDateTime.now().plusHours(2);
    private final Sala salaAtiva = new Sala(1L, "Sala 1", 10, true, "Bloco A");
    private final Usuario usuarioAtivo = new Usuario(1L, "Pedro", "pedro@email.com", true, "Dev");

    @Test
    @DisplayName("Deve criar reserva com sucesso quando o horário está livre")
    void deveCriarReserva_QuandoHorarioEstaLivre() {
        // Arrange
        ReservaRequestDTO dto = new ReservaRequestDTO(1L, 1L, inicio, fim);

        when(salaRepository.findById(1L)).thenReturn(Optional.of(salaAtiva));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioAtivo));
        when(reservaRepository.findConflitos(1L, inicio, fim)).thenReturn(Collections.emptyList());

        when(reservaRepository.save(any(Reserva.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Reserva novaReserva = reservaService.criarReserva(dto);

        // Assert
        assertNotNull(novaReserva);
        assertEquals(StatusReserva.ATIVA, novaReserva.getStatus());
        assertEquals(salaAtiva, novaReserva.getSala());
        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando houver conflito de horário")
    void deveLancarExcecao_QuandoHouverConflito() {
        // Arrange
        ReservaRequestDTO dto = new ReservaRequestDTO(1L, 1L, inicio, fim);
        Reserva reservaExistente = new Reserva(100L, salaAtiva, usuarioAtivo, inicio, fim, StatusReserva.ATIVA);

        when(salaRepository.findById(1L)).thenReturn(Optional.of(salaAtiva));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioAtivo));
        when(reservaRepository.findConflitos(1L, inicio, fim)).thenReturn(List.of(reservaExistente));

        // Act & Assert
        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            reservaService.criarReserva(dto);
        });

        assertEquals("Horário indiponível. Já existe uma reserva ativa neste período.", exception.getMessage());
        verify(reservaRepository, never()).save(any(Reserva.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar reservar uma sala inativa")
    void deveLancarExcecao_QuandoSalaInativa() {
        // Arrange
        Sala salaInativa = new Sala(2L, "Sala 2", 5, false, "Bloco B");
        ReservaRequestDTO dto = new ReservaRequestDTO(2L, 1L, inicio, fim);

        when(salaRepository.findById(2L)).thenReturn(Optional.of(salaInativa));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioAtivo));

        // Act & Assert
        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            reservaService.criarReserva(dto);
        });

        assertEquals("Não é possível reservar uma sala inativa.", exception.getMessage());
        verify(reservaRepository, never()).save(any(Reserva.class));
    }
}
