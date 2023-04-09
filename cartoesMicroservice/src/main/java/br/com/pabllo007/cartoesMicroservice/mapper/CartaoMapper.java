package br.com.pabllo007.cartoesMicroservice.mapper;

import br.com.pabllo007.cartoesMicroservice.dto.CartaoDto;
import br.com.pabllo007.cartoesMicroservice.dto.CartaoSaveDto;
import br.com.pabllo007.cartoesMicroservice.entities.Cartao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CartaoDto> toDtos(List<Cartao> cartoes) {
        return cartoes.stream().map(cartao -> new CartaoDto(
                cartao.getId(),
                cartao.getNome(),
                cartao.getBandeira(),
                cartao.getRenda(),
                cartao.getLimiteBasico()
        )).collect(Collectors.toList());

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
