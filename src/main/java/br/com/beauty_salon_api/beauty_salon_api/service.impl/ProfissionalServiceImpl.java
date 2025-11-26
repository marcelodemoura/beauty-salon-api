package br.com.beauty_salon_api.beauty_salon_api.service.impl;

import br.com.beauty_salon_api.beauty_salon_api.entity.Profissional;
import java.util.List;

public interface ProfissionalServiceImpl  {


        Profissional salvar(Profissional profissional);

        Profissional atualizar(Long id, Profissional profissional);

        void deletar(Long id);

        Profissional buscarPorId(Long id);

        List<Profissional> listarTodos();

    }
