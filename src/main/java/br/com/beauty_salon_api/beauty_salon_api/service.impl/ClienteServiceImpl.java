package br.com.beauty_salon_api.beauty_salon_api.service.impl;

import br.com.beauty_salon_api.beauty_salon_api.entity.Cliente;

import java.util.List;

public interface ClienteServiceImpl {


    Cliente salvar(Cliente cliente);

    Cliente buscarPorId(Long id);

    List<Cliente> listarTodos();

    Cliente atualizar(Long id, Cliente cliente);

    void deletar(Long id);

}
