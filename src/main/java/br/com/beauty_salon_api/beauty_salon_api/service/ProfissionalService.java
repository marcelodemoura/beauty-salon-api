package br.com.beauty_salon_api.beauty_salon_api.service;

import br.com.beauty_salon_api.beauty_salon_api.entity.Profissional;
import br.com.beauty_salon_api.beauty_salon_api.repository.ProfissionalRepository;
import br.com.beauty_salon_api.beauty_salon_api.service.impl.ProfissionalServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfissionalService implements ProfissionalServiceImpl {

    private final ProfissionalRepository profissionalRepository;

    public ProfissionalService(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }


    public Profissional salvar(Profissional profissionalService) {
        return profissionalRepository.save(profissionalService);
    }

    public Profissional atualizar(Long id, Profissional profissional) {
        Profissional existente = buscarPorId(id);

        existente.setNome(profissional.getNome());
        existente.setEspecialidade(profissional.getEspecialidade());
        existente.setTelefone(profissional.getTelefone());
        existente.setAtivo(profissional.getAtivo());

        return profissionalRepository.save(existente);
    }

    public void deletar(Long id) {
        profissionalRepository.deleteById(id);
    }

    public Profissional buscarPorId(Long id) {
        return profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado!"));
    }

    public List<Profissional> listarTodos() {
        return profissionalRepository.findAll();
    }

}
