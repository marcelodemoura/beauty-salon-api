package br.com.beauty_salon_api.beauty_salon_api.service;

import br.com.beauty_salon_api.beauty_salon_api.entity.Agendamento;
import br.com.beauty_salon_api.beauty_salon_api.entity.Cliente;
import br.com.beauty_salon_api.beauty_salon_api.entity.Profissional;
import br.com.beauty_salon_api.beauty_salon_api.repository.AgendamentoRepository;
import br.com.beauty_salon_api.beauty_salon_api.repository.ClienteRepository;
import br.com.beauty_salon_api.beauty_salon_api.repository.ProfissionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ProfissionalRepository profissionalRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              ClienteRepository clienteRepository,
                              ProfissionalRepository profissionalRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.profissionalRepository = profissionalRepository;
    }

    public Agendamento criar(Agendamento agendamento) {

        Cliente cliente = clienteRepository.findById(agendamento.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Profissional profissional = profissionalRepository.findById(agendamento.getProfissional().getId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        // validação:
        List<Agendamento> conflitos =
                agendamentoRepository.verificarConflitos(
                        profissional.getId(),
                        agendamento.getDataHora(),
                        agendamento.getDataFim()
                );

        if (!conflitos.isEmpty()) {
            throw new RuntimeException("Horário já está ocupado para este profissional");
        }

        agendamento.setCliente(cliente);
        agendamento.setProfissional(profissional);

        return agendamentoRepository.save(agendamento);

    }

    public Agendamento atualizar(Long id, Agendamento agendamento) {

        Agendamento existente = buscarPorId(id);

        Cliente cliente = clienteRepository.findById(agendamento.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Profissional profissional = profissionalRepository.findById(agendamento.getProfissional().getId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        existente.setCliente(cliente);
        existente.setProfissional(profissional);
        existente.setDataHora(agendamento.getDataHora());
        existente.setDataFim(agendamento.getDataFim());
        existente.setObservacao(agendamento.getObservacao());

        return agendamentoRepository.save(existente);
    }

    public void deletar(Long id) {
        agendamentoRepository.deleteById(id);
    }

    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

}
