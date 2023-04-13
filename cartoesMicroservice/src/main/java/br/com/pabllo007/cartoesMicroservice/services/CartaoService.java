package br.com.pabllo007.cartoesMicroservice.services;

import br.com.pabllo007.cartoesMicroservice.entities.Cartao;
import br.com.pabllo007.cartoesMicroservice.repositories.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaRepository;

    @Transactional
    public Cartao save(Cartao cartao) {
        return cartaRepository.save(cartao);
    }

    public List<Cartao> getCartaoRendaMenorIgual(Long renda) {
        var rendaMenorIgual = BigDecimal.valueOf(renda);
        return cartaRepository.findByRendaLessThanEqual(rendaMenorIgual);
    }
}
