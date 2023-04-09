package br.com.pabllo007.cartoesMicroservice.repositories;

import br.com.pabllo007.cartoesMicroservice.entities.CartaoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteCartaoRepository extends JpaRepository<CartaoCliente, Long> {
    List<CartaoCliente> findByCpf(String cpf);
}
