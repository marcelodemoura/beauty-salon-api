package br.com.beauty_salon_api.beauty_salon_api.service.impl;


import br.com.beauty_salon_api.beauty_salon_api.entity.Servico;

import java.util.List;

public interface ServicoServiceImpl {


    Servico salvar(Servico servico);
    Servico atualizar(Long id, Servico servico);
    void deletar(Long id);
    Servico buscarPorId(Long id);
    List<Servico> listarTodos();

    }
