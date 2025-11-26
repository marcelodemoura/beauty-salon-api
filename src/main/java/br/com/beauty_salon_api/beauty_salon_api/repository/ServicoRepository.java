package br.com.beauty_salon_api.beauty_salon_api.repository;

import br.com.beauty_salon_api.beauty_salon_api.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
