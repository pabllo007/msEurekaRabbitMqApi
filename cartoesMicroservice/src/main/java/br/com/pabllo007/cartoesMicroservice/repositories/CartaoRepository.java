package br.com.pabllo007.cartoesMicroservice.repositories;

import br.com.pabllo007.cartoesMicroservice.entities.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    List<Cartao> findByRendaLessThanEqual(BigDecimal renda);
}
