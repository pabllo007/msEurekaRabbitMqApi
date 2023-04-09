package br.com.pabllo007.cartoesMicroservice.mapper;

import br.com.pabllo007.cartoesMicroservice.dto.CartaoDto;
import br.com.pabllo007.cartoesMicroservice.dto.CartaoSaveDto;
import br.com.pabllo007.cartoesMicroservice.entities.Cartao;
import org.springframework.stereotype.Component;

@Component
public class CartaoMapper {

    public CartaoDto toDto(Cartao cartao) {
        return new CartaoDto(
                cartao.getId(),
                cartao.getNome(),
                cartao.getBandeira(),
                cartao.getRenda(),
                cartao.getLimiteBasico()
        );
    }

    public Cartao toEntity(CartaoSaveDto cartaoSave) {
        return new Cartao(
                cartaoSave.getNome(),
                cartaoSave.getBandeira(),
                cartaoSave.getRenda(),
                cartaoSave.getLimite()
        );
    }

}
