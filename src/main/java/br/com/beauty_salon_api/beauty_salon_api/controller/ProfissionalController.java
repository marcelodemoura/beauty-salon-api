package br.com.beauty_salon_api.beauty_salon_api.controller;

import br.com.beauty_salon_api.beauty_salon_api.entity.Profissional;
import br.com.beauty_salon_api.beauty_salon_api.service.ProfissionalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private ProfissionalService profissionalService;

    @PostMapping
    public Profissional salvar(@RequestBody Profissional profissional) {
        return profissionalService.salvar(profissional);
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
