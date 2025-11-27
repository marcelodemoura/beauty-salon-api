package br.com.beauty_salon_api.beauty_salon_api.controller;

import br.com.beauty_salon_api.beauty_salon_api.entity.Profissional;
import br.com.beauty_salon_api.beauty_salon_api.service.ProfissionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @PostMapping
    public ResponseEntity<Profissional> salvar(@RequestBody Profissional profissional) {
        return ResponseEntity.ok(profissionalService.salvar(profissional));
    }

    @PutMapping("/{id}")
    public Profissional atualizar(
            @PathVariable Long id,
            @RequestBody Profissional profissional
    ) {
        return profissionalService.atualizar(id, profissional);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        profissionalService.deletar(id);
    }

    @GetMapping("/{id}")
    public Profissional buscarPorId(@PathVariable Long id) {
        return profissionalService.buscarPorId(id);
    }

    @GetMapping
    public List<Profissional> listarTodos() {
        return profissionalService.listarTodos();
    }

}
