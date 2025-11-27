package br.com.beauty_salon_api.beauty_salon_api.service;

import br.com.beauty_salon_api.beauty_salon_api.dto.AgendamentoRequestDTO;
import br.com.beauty_salon_api.beauty_salon_api.dto.AgendamentoResponseDTO;
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

    public AgendamentoResponseDTO criar(AgendamentoRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Profissional profissional = profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        // Verifica conflito de horários
        boolean existeConflito = !agendamentoRepository.verificarConflitos(
                profissional.getId(),
                dto.getDataHora(),
                dto.getDataFim()
        ).isEmpty();

        if (existeConflito) {
            throw new RuntimeException("Horário indisponível para este profissional");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setProfissional(profissional);
        agendamento.setDataHora(dto.getDataHora());
        agendamento.setDataFim(dto.getDataFim());
        agendamento.setObservacao(dto.getObservacao());

        Agendamento salvo = agendamentoRepository.save(agendamento);

        return mapToResponse(salvo);
    }

    private AgendamentoResponseDTO mapToResponse(Agendamento ag) {
        AgendamentoResponseDTO dto = new AgendamentoResponseDTO();
        dto.setId(ag.getId());
        dto.setCliente(ag.getCliente().getNome());
        dto.setProfissional(ag.getProfissional().getNome());
        dto.setDataHora(ag.getDataHora());
        dto.setDataFim(ag.getDataFim());
        dto.setObservacao(ag.getObservacao());
        return dto;
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
