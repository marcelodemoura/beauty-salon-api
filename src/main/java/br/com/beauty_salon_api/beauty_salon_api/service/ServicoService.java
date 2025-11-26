package br.com.beauty_salon_api.beauty_salon_api.service;

import br.com.beauty_salon_api.beauty_salon_api.entity.Servico;
import br.com.beauty_salon_api.beauty_salon_api.repository.ServicoRepository;

import br.com.beauty_salon_api.beauty_salon_api.service.impl.ServicoServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService implements ServicoServiceImpl {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public Servico salvar(Servico servico) {
        return servicoRepository.save(servico);
    }


    public Servico atualizar(Long id, Servico servico) {
        return null;
    }

    public void deletar(Long id) {

    }

    public Servico buscarPorId(Long id) {
        return null;
    }

    public List<Servico> listarTodos() {
        return List.of();
    }
}
