package br.com.beauty_salon_api.beauty_salon_api.repository;

import br.com.beauty_salon_api.beauty_salon_api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    Optional<Cliente> findByNome(String nomeCliente);
}