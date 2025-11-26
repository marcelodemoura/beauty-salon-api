package br.com.beauty_salon_api.beauty_salon_api.service.impl;

import br.com.beauty_salon_api.beauty_salon_api.entity.Agendamento;
import java.util.List;

public interface AgendamentoServiceImpl {


    Agendamento criar(Agendamento agendamento);

    Agendamento atualizar(Long id, Agendamento agendamento);

    void deletar(Long id);

    Agendamento buscarPorId(Long id);

    List<Agendamento> listarTodos();

}
