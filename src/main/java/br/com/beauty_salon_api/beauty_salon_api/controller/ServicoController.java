package br.com.beauty_salon_api.beauty_salon_api.controller;

import br.com.beauty_salon_api.beauty_salon_api.entity.Servico;
import br.com.beauty_salon_api.beauty_salon_api.service.ServicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicoController {

 private final ServicoService servicoservice;

    public ServicoController(ServicoService servicoservice) {
        this.servicoservice = servicoservice;
    }

    @PostMapping
    public Servico salvar(@RequestBody Servico servico) {
        return servicoservice.salvar(servico);
    }

    @PutMapping("/{id}")
    public Servico atualizar(@PathVariable Long id, @RequestBody Servico servico) {
        return servicoservice.atualizar(id, servico);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        servicoservice.deletar(id);
    }

    @GetMapping("/{id}")
    public Servico buscarPorId(@PathVariable Long id) {
        return servicoservice.buscarPorId(id);
    }

    @GetMapping
    public List<Servico> listarTodos() {
        return servicoservice.listarTodos();
    }
}
