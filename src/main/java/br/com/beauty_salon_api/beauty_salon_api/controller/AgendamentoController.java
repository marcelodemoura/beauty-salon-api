package br.com.beauty_salon_api.beauty_salon_api.controller;

import br.com.beauty_salon_api.beauty_salon_api.dto.AgendamentoRequestDTO;
import br.com.beauty_salon_api.beauty_salon_api.entity.Agendamento;
import br.com.beauty_salon_api.beauty_salon_api.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping("/agendamentos")
    public ResponseEntity<Agendamento> criar(@RequestBody AgendamentoRequestDTO request) {

        Agendamento agendamento = agendamentoService.criarAgendamento(
                request.getNomeCliente(),
                request.getServicoId(),
                request.getData(),
                request.getHora()
        );

        return ResponseEntity.ok(agendamento);
    }


    @PutMapping("/{id}")
    public Agendamento atualizar(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        return agendamentoService.atualizar(id, agendamento);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        agendamentoService.deletar(id);
    }

    @GetMapping("/{id}")
    public Agendamento buscarPorId(@PathVariable Long id) {
        return agendamentoService.buscarPorId(id);
    }

    @GetMapping
    public List<Agendamento> listarTodos() {
        return agendamentoService.listarTodos();
    }


}
