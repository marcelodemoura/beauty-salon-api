package br.com.beauty_salon_api.beauty_salon_api.repository;

import br.com.beauty_salon_api.beauty_salon_api.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
