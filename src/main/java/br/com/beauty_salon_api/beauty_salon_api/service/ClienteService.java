package br.com.beauty_salon_api.beauty_salon_api.service;

import br.com.beauty_salon_api.beauty_salon_api.entity.Cliente;
import br.com.beauty_salon_api.beauty_salon_api.repository.ClienteRepository;
import br.com.beauty_salon_api.beauty_salon_api.service.impl.ClienteServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements ClienteServiceImpl {


    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente cliente = buscarPorId(id);
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setTelefone(clienteAtualizado.getTelefone());
        cliente.setEmail(clienteAtualizado.getEmail());
        return clienteRepository.save(cliente);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }



}
