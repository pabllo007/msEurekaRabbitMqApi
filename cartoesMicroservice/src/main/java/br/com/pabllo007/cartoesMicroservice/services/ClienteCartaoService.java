package br.com.pabllo007.cartoesMicroservice.services;

import br.com.pabllo007.cartoesMicroservice.entities.CartaoCliente;
import br.com.pabllo007.cartoesMicroservice.repositories.ClienteCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteCartaoService {

    @Autowired
    private ClienteCartaoRepository clienteCartaoRepository;

    public List<CartaoCliente> listaCartaoByCliente(String cpf){
        return clienteCartaoRepository.findByCpf(cpf);
    }
}
