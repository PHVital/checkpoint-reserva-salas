package br.com.alura.ResevaSalas.repository;

import br.com.alura.ResevaSalas.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("SELECT r FROM Reserva r WHERE r.sala.id = :salaId " +
            "AND r.status = 'ATIVA' " +
            "AND r.inicio < :novoFim " +
            "AND r.fim > :novoInicio")
    List<Reserva> findConflitos(Long salaId, LocalDateTime novoInicio, LocalDateTime novoFim);
}
