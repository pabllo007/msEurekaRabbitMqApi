package br.com.pabllo007.clientemicroservice.service;

import br.com.pabllo007.clientemicroservice.entities.Cliente;
import br.com.pabllo007.clientemicroservice.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> getByCPF(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }
}
