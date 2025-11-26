package br.com.beauty_salon_api.beauty_salon_api.repository;

import br.com.beauty_salon_api.beauty_salon_api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
