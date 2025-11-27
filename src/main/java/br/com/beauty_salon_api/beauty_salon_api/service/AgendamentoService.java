package br.com.beauty_salon_api.beauty_salon_api.service;

import br.com.beauty_salon_api.beauty_salon_api.dto.AgendamentoRequestDTO;
import br.com.beauty_salon_api.beauty_salon_api.dto.AgendamentoResponseDTO;
import br.com.beauty_salon_api.beauty_salon_api.entity.Agendamento;
import br.com.beauty_salon_api.beauty_salon_api.entity.Cliente;
import br.com.beauty_salon_api.beauty_salon_api.entity.Profissional;
import br.com.beauty_salon_api.beauty_salon_api.entity.Servico;
import br.com.beauty_salon_api.beauty_salon_api.repository.AgendamentoRepository;
import br.com.beauty_salon_api.beauty_salon_api.repository.ClienteRepository;
import br.com.beauty_salon_api.beauty_salon_api.repository.ProfissionalRepository;
import br.com.beauty_salon_api.beauty_salon_api.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              ClienteRepository clienteRepository,
                              ProfissionalRepository profissionalRepository,
                              ServicoRepository servicoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.servicoRepository = servicoRepository;
    }

    // -----------------------------------------------------------
    // 📌 CRIAR AGENDAMENTO
    // -----------------------------------------------------------
    public AgendamentoResponseDTO criar(AgendamentoRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Profissional profissional = profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        // Buscar serviços
        List<Servico> servicos = servicoRepository.findAllById(dto.getServicosIds());
        if (servicos.isEmpty()) {
            throw new RuntimeException("Nenhum serviço selecionado");
        }

        // Soma total da duração dos serviços
        int duracaoTotal = servicos.stream()
                .mapToInt(Servico::getDuracaoMinutos)
                .sum();

        // Dados de horário
        LocalDateTime inicio = dto.getDataHora();
        LocalDateTime fim = inicio.plusMinutes(duracaoTotal);

        // Fim com deslocamento obrigatório
        LocalDateTime fimComDeslocamento = fim.plusMinutes(30);

        // Validar horário permitido (apenas início)
        validarDisponibilidade(inicio);

        // Verificar conflito com outros agendamentos
        boolean existeConflito = !agendamentoRepository.verificarConflitos(
                profissional.getId(),
                inicio,
                fimComDeslocamento
        ).isEmpty();

        if (existeConflito) {
            throw new RuntimeException("Horário indisponível. Existe outro atendimento muito próximo.");
        }

        // Criar e salvar agendamento
        Agendamento ag = new Agendamento();
        ag.setCliente(cliente);
        ag.setProfissional(profissional);
        ag.setServicos(servicos);
        ag.setDataHora(inicio);
        ag.setDataFim(fim);
        ag.setObservacao(dto.getObservacao());

        Agendamento salvo = agendamentoRepository.save(ag);
        return mapToResponse(salvo);
    }

    // -----------------------------------------------------------
    // 📌 DTO de saída
    // -----------------------------------------------------------
    private AgendamentoResponseDTO mapToResponse(Agendamento ag) {
        AgendamentoResponseDTO dto = new AgendamentoResponseDTO();
        dto.setId(ag.getId());
        dto.setCliente(ag.getCliente().getNome());
        dto.setProfissional(ag.getProfissional().getNome());
        dto.setDataHora(ag.getDataHora());
        dto.setDataFim(ag.getDataFim());
        dto.setObservacao(ag.getObservacao());

        dto.setServicos(
                ag.getServicos().stream()
                        .map(Servico::getNome)
                        .toList()
        );

        return dto;
    }

    // -----------------------------------------------------------
    // 📌 ATUALIZAR (versão simples)
    // -----------------------------------------------------------
    public Agendamento atualizar(Long id, Agendamento agendamento) {
        Agendamento existente = buscarPorId(id);

        existente.setCliente(agendamento.getCliente());
        existente.setProfissional(agendamento.getProfissional());
        existente.setServicos(agendamento.getServicos());
        existente.setDataHora(agendamento.getDataHora());
        existente.setDataFim(agendamento.getDataFim());
        existente.setObservacao(agendamento.getObservacao());

        return agendamentoRepository.save(existente);
    }

    // -----------------------------------------------------------
    // 📌 CRUD Simples
    // -----------------------------------------------------------
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

    // -----------------------------------------------------------
    // 📌 Validação — Apenas o horário de INÍCIO importa
    // -----------------------------------------------------------
    private void validarDisponibilidade(LocalDateTime inicio) {

        DayOfWeek dia = inicio.getDayOfWeek();
        int hora = inicio.getHour();

        switch (dia) {

            case MONDAY: // Segunda 12–18
                if (hora < 12 || hora >= 18) {
                    throw new RuntimeException("Segunda disponível somente entre 12h e 18h");
                }
                break;

            case TUESDAY:
                throw new RuntimeException("Terça-feira sem atendimento");

            case WEDNESDAY: // Quarta 14–18
                if (hora < 14 || hora >= 18) {
                    throw new RuntimeException("Quarta disponível somente entre 14h e 18h");
                }
                break;

            case THURSDAY: // Quinta 12–18
                if (hora < 12 || hora >= 18) {
                    throw new RuntimeException("Quinta disponível somente entre 12h e 18h");
                }
                break;

            case FRIDAY: // Sexta 14–18
                if (hora < 14 || hora >= 18) {
                    throw new RuntimeException("Sexta disponível somente entre 14h e 18h");
                }
                break;

            case SATURDAY: // Sábado 08–12
                if (hora < 8 || hora >= 12) {
                    throw new RuntimeException("Sábado disponível somente entre 08h e 12h");
                }
                break;

            case SUNDAY: // Domingo 08–12
                if (hora < 8 || hora >= 12) {
                    throw new RuntimeException("Domingo disponível somente entre 08h e 12h");
                }
                break;
        }
    }
}
