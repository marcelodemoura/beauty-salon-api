package br.com.beauty_salon_api.beauty_salon_api.repository;

import br.com.beauty_salon_api.beauty_salon_api.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("""
       SELECT a FROM Agendamento a
       WHERE a.profissional.id = :idProfissional
       AND (
            (a.dataHora <= :dataHora AND a.dataFim > :dataHora) OR
            (a.dataHora < :dataFim AND a.dataFim >= :dataFim) OR
            (a.dataHora >= :dataHora AND a.dataFim <= :dataFim)
       )
       """)
    List<Agendamento> verificarConflitos(
            Long idProfissional,
            LocalDateTime dataHora,
            LocalDateTime dataFim
    );


}
